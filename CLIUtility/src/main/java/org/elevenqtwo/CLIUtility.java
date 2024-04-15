package org.elevenqtwo;

import org.elevenqtwo.model.PackageModel;
import org.elevenqtwo.service.PackageComparator;
import org.elevenqtwo.service.PackageFetcher;
import org.elevenqtwo.util.JsonWriter;

import java.io.File;
import java.util.List;

public class CLIUtility {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java " +
                    "-jar PackageComparator-1.0-SNAPSHOT.jar " +
                    "<branch1> <branch2> <outputFilePath>");
            return;
        }

        String branch1 = args[0];
        String branch2 = args[1];
        String filePath = args[2];

        PackageFetcher packageFetcher = new PackageFetcher();
        PackageComparator packageComparator = new PackageComparator();

        File file = new File(filePath);
        if (!file.getParentFile().exists() || file.isDirectory()) {
            System.err.println("Error: Invalid output file path.");
            return;
        }

        List<PackageModel> packagesModels1 = packageFetcher.fetchPackages(branch1);

        if (packagesModels1 == null || packagesModels1.isEmpty()) {
            System.err.println("Error: Unable to fetch packages for branch " +  branch1);
            return;
        }

        List<PackageModel> packagesModels2 = packageFetcher.fetchPackages(branch2);

        if (packagesModels2 == null || packagesModels2.isEmpty()) {
            System.err.println("Error: Unable to fetch packages for branch " + branch2);
            return;
        }

        JsonWriter.writeJSONToFile(
                packageComparator.comparePackages(
                        packagesModels1,
                        packagesModels2),
                filePath);
    }
}