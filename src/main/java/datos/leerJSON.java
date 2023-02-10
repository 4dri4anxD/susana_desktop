package datos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class leerJSON {

    public leerJSON() {

    }

    public String getString(String key) {
        // parsing file "JSONExample.json"
        try {
            Object ob = new JSONParser().parse(new FileReader("strings.json"));
            // typecasting ob to JSONObject
            JSONObject js = (JSONObject) ob;

            String data = (String) js.get(key);
            return data;
        } catch (Exception ex) {
            System.out.println("EX: " + ex);
            return "";
        }

    }

}
