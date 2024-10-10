package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class task3 {
    public task3() {
    }

    public static void main(String[] args) {
        String testsJsonPath = "tests.json";
        String valuesJsonPath = "values.json";
        String reportJsonPath = "report.json";

        try {
            String testsJson = new String(Files.readAllBytes(Paths.get(testsJsonPath)));
            JsonObject testsObject = JsonParser.parseString(testsJson).getAsJsonObject();
            String valuesJson = new String(Files.readAllBytes(Paths.get(valuesJsonPath)));
            JsonObject valuesObject = JsonParser.parseString(valuesJson).getAsJsonObject();
            updateTestValues(testsObject, valuesObject);
            Gson gson = new Gson();
            String reportJson = gson.toJson(testsObject);

            try {
                FileWriter writer = new FileWriter(reportJsonPath);

                try {
                    writer.write(reportJson);
                } catch (Throwable var14) {
                    try {
                        writer.close();
                    } catch (Throwable var13) {
                        var14.addSuppressed(var13);
                    }

                    throw var14;
                }

                writer.close();
            } catch (IOException var15) {
                IOException e = var15;
                e.printStackTrace();
            }
        } catch (IOException var16) {
            IOException e = var16;
            e.printStackTrace();
        }

    }

    private static void updateTestValues(JsonObject testsObject, JsonObject valuesObject) {
        int testId = testsObject.get("id").getAsInt();
        String testValue = getValueValue(valuesObject, testId);
        testsObject.addProperty("value", testValue);
        JsonArray valuesArray = testsObject.getAsJsonArray("values");
        if (valuesArray != null) {
            Iterator var5 = valuesArray.iterator();

            while(var5.hasNext()) {
                JsonElement valueElement = (JsonElement)var5.next();
                JsonObject valueObject = valueElement.getAsJsonObject();
                int valueId = valueObject.get("id").getAsInt();
                String valueValue = getValueValue(valuesObject, valueId);
                valueObject.addProperty("value", valueValue);
            }
        }

    }

    private static String getValueValue(JsonObject valuesObject, int valueId) {
        JsonArray valuesArray = valuesObject.getAsJsonArray("values");
        Iterator var3 = valuesArray.iterator();

        JsonObject valueObject;
        int id;
        do {
            if (!var3.hasNext()) {
                return "";
            }

            JsonElement valueElement = (JsonElement)var3.next();
            valueObject = valueElement.getAsJsonObject();
            id = valueObject.get("id").getAsInt();
        } while(id != valueId);

        return valueObject.get("value").getAsString();
    }
}
