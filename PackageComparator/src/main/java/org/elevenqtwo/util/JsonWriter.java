package org.elevenqtwo.util;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {
    public static void writeJSONToFile(JSONObject jsonObject, String filePath) {
        if (jsonObject == null || filePath == null || filePath.isEmpty()) {
            System.err.println("Error: JSON object or file path is null or empty.");
            return;
        }

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonObject.toString(4));
            file.flush();
        } catch (IOException e) {
            System.err.println("Error writing JSON to file: " + e.getMessage());
        }
    }
}
