package org.elevenqtwo.service;

import org.elevenqtwo.model.PackageModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PackageComparator {

    public JSONObject comparePackages(List<PackageModel> packages1, List<PackageModel> packages2) {
        Map<String, PackageModel> packageMap1 = packages1.stream()
                .collect(Collectors.toMap(PackageModel::getName, packageModel -> packageModel));

        List<PackageModel> onlyIn1 = new ArrayList<>();
        List<PackageModel> onlyIn2 = new ArrayList<>();
        List<PackageModel> greaterIn1 = new ArrayList<>();

        for (PackageModel package2 : packages2) {
            PackageModel package1 = packageMap1.get(package2.getName());
            if (package1 == null) {
                onlyIn2.add(package2);
            } else {
                int comparisonResult = compareVersions(package1.getVersion(), package2.getVersion());
                if (comparisonResult > 0) {
                    greaterIn1.add(package1);
                } else if (comparisonResult < 0) {
                    onlyIn1.add(package1);
                }
            }
        }

        JSONObject result = new JSONObject();
        result.put("onlyIn1", new JSONArray(onlyIn1));
        result.put("onlyIn2", new JSONArray(onlyIn2));
        result.put("greaterIn1", new JSONArray(greaterIn1));

        return result;
    }

    private int compareVersions(String version1, String version2) {
        String[] parts1 = version1.split("\\.");
        String[] parts2 = version2.split("\\.");

        int length = Math.max(parts1.length, parts2.length);

        for (int i = 0; i < length; i++) {
            String part1 = i < parts1.length ? parts1[i] : "0";
            String part2 = i < parts2.length ? parts2[i] : "0";

            int result = compareNumberParts(part1, part2);
            if (result != 0) {
                return result;
            }
        }

        return 0;
    }

    private int compareNumberParts(String part1, String part2) {
        // If both parts are numbers, compare them as integers.
        if (part1.matches("\\d+") && part2.matches("\\d+")) {
            int v1 = Integer.parseInt(part1);
            int v2 = Integer.parseInt(part2);
            return Integer.compare(v1, v2);
        }

        // If only one part is a number, return a positive or negative integer
        // depending on whether the number is greater or less than the other part.
        if (part1.matches("\\d+") && !part2.matches("\\d+")) {
            return 1;
        }
        if (!part1.matches("\\d+") && part2.matches("\\d+")) {
            return -1;
        }

        // If neither part is a number, compare them as strings.
        return part1.compareTo(part2);
    }
}
