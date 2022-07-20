package io.osemwota.unit_test_sample.util;

import com.google.common.io.Resources;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper for JSON files.
 */
public class ParseJsonHelper {

    private ParseJsonHelper() {}

    public static String SUCCESSFUL_RESPONSE = "{\n" +
            "  \"id\": 53,\n" +
            "  \"type\": \"general\",\n" +
            "  \"setup\": \"How do you make holy water ?\",\n" +
            "  \"punchline\" : \"You boil the hell out of it\"\n" +
            "}";

    /**
     * Converts JSON file to {@link String }
     * @param path The file path of the json file.
     * @return The JSON file as {@link String}
     * @throws IOException
     */
    @SuppressWarnings("UnstableApiUsage")
    public static String getJson(String path) throws IOException {
        URL uri = Resources.getResource(path);
        File file = new File(uri.getPath());
        InputStream fileStream = uri.openStream();
//        return new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

        return readJSON(fileStream);
    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    private static String readJSON(InputStream inputStream) {
        String json;
        try {
            // Opening data.json file
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            // read values in the byte array
            int read = inputStream.read(buffer);
            inputStream.close();
            // convert byte to string
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    public static JSONObject getJokesSuccessResponse() throws JSONException {
        JSONObject successResponse = new JSONObject();
        successResponse.put("id", 53);
        successResponse.put("type", "general");
        successResponse.put("setup", "How do you make holy water ?");
        successResponse.put("punchline","You boil the hell out of it");
        return successResponse;
    }

    public static String randomChuckNorrisSuccessResponse() {
        Map<String, String> successResponse = new HashMap<>();

        successResponse.put("iconUrl", "https://assets.chucknorris.host/img/avatar/chuck-norris.png");
        successResponse.put("id", "v5bjnMaRTk2dW3lCGAfkVQ");
        successResponse.put("url", "http://chucknorris.net/23333");
        successResponse.put("value", "Ok im back. Chuck Norris was secretly included for Mortal Kombat X. The developers then changed their minds and removed him because 1 hit from Chuck Norris= a fatality.");

        return successResponse.toString();
    }
}
