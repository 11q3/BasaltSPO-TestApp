package org.elevenqtwo.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.elevenqtwo.model.PackageModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class PackageFetcher {
    private static final Gson gson = new GsonBuilder().create();

    public List<PackageModel> fetchPackages(String branch) {
        List<PackageModel> packages = new LinkedList<>();
        String urlString = "https://rdb.altlinux.org/api/export/branch_binary_packages/" + branch;

        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Upgrade", "h2c");

            try (InputStreamReader inputStreamReader = new InputStreamReader(con.getInputStream());
                 BufferedReader reader = new BufferedReader(inputStreamReader);
                 JsonReader jsonReader = new JsonReader(reader)) {

                jsonReader.beginObject();

                while (jsonReader.hasNext()) {
                    String name = jsonReader.nextName();
                    if (name.equals("packages")) {
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            PackageModel packageModel = gson.fromJson(jsonReader, PackageModel.class);
                            packages.add(packageModel);
                        }
                        jsonReader.endArray();
                    } else {
                        jsonReader.skipValue();
                    }
                }
                jsonReader.endObject();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return packages;
    }
}