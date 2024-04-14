package org.elevenqtwo;

import org.elevenqtwo.model.PackageModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PackageFetcher {

    public List<PackageModel> fetchPackages(String branch, String arch) {
        List<PackageModel> packages = new ArrayList<>();
        String urlString = "https://rdb.altlinux.org/api/export/branch_binary_packages/%s?arch=%s";
        urlString = String.format(urlString, branch, arch);

        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray packagesArray = jsonResponse.getJSONArray("packages");

                for (int i = 0; i < packagesArray.length(); i++) {

                    JSONObject packageObject = packagesArray.getJSONObject(i);
                    String name = packageObject.getString("name");
                    String version = packageObject.getString("version");

                    packages.add(new PackageModel(name, version));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return packages;
    }
}