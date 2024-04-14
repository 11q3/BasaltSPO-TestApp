package org.elevenqtwo.util;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {
    public static void writeJSONToFile(JSONObject jsonObject, String filePath) {
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonObject.toString(4));
            file.flush();
        } catch (IOException e) {
            System.err.println("Error writing JSON to file: " + e.getMessage());
        }
    }
}
