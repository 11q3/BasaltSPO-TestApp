package org.elevenqtwo.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.elevenqtwo.model.PackageModel;

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
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(in, JsonObject.class);
                JsonArray packagesArray = jsonObject.get("packages").getAsJsonArray();

                packages = gson.fromJson(packagesArray, new TypeToken<List<PackageModel>>() {}.getType());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return packages;
    }
}