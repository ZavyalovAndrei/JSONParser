import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws ParseException {
        String json = readString("new_data.json");
        List<Employee> list = jsonToList(json);
        print(list);
    }

    private static String readString(String fileName) {
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                return line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    private static List<Employee> jsonToList(String jsonString) throws ParseException {
        List<Employee> result = new ArrayList<>();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonString);
        JSONArray list = (JSONArray) obj;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        for (Object data : list) {
            Employee employee = gson.fromJson(String.valueOf(data), Employee.class);
            result.add(employee);
        }
        return result;
    }

    private static void print(List<Employee> list) {
        System.out.println("\n> Task :Main.main()");
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }
}
