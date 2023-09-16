package Utilites;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;

public class PropertiesHandler {

    JSONObject expactedJsonObj;
    public String json(String keyword ) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        expactedJsonObj = (JSONObject) parser.parse(new FileReader("src/test/java/Resources/TestDataFile.json"));
        expactedJsonObj.get(keyword);

        return (String) expactedJsonObj.get(keyword);
    }

}