import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Task3 {

    private static String readFileToString(String path, Charset encoding) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)), encoding);
    }

    private static void createRecursiveReport(JSONArray testsJSONArray, HashMap<Integer, String> valuesHashMap) throws JSONException {
        for (int i = 0; i < testsJSONArray.length(); i++) {
            JSONObject testJSONObject = testsJSONArray.getJSONObject(i);
            testJSONObject.put("value", valuesHashMap.get(testJSONObject.getInt("id")));

            if (testJSONObject.has("values"))
                createRecursiveReport(testJSONObject.getJSONArray("values"), valuesHashMap);
        }
    }

    public static void main(String[] args) throws IOException, JSONException {

        String pathReport = "./report.json";

        String tests = readFileToString(args[0], StandardCharsets.US_ASCII);
        JSONObject testsJSONObject = new JSONObject(tests);
        JSONArray testsJSONArray = testsJSONObject.getJSONArray("tests");

        String values = readFileToString(args[1], StandardCharsets.US_ASCII);
        JSONObject valuesJSONObject = new JSONObject(values);
        JSONArray valuesJSONArray = valuesJSONObject.getJSONArray("values");

        HashMap<Integer, String> valuesHashMap = new HashMap<>();
        for (int i = 0; i < valuesJSONArray.length(); i++) {
            JSONObject valueJSONObject = valuesJSONArray.getJSONObject(i);
            valuesHashMap.put(valueJSONObject.getInt("id"), valueJSONObject.getString("value"));
        }

        createRecursiveReport(testsJSONArray, valuesHashMap);
        Files.write(Paths.get(pathReport), testsJSONObject.toString(2)
                .getBytes(StandardCharsets.US_ASCII));
    }

}
