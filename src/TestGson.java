import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import netscape.javascript.JSObject;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestGson {
    public static void main(String[] args) {
        try {
            // create a map
            Map<String, Object> map = new HashMap<>();
            map.put("name", "John Deo");
            map.put("email", "john.doe@example.com");
            map.put("roles", new String[]{"Member", "Admin"});
            map.put("admin", true);

            // create a writer
            Writer writer = new FileWriter("user.json");

            // convert map to JSON File

            new Gson().toJson(map, writer);

            // close the writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

