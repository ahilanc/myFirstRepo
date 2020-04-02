package Helpers;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class JsonDataHelper {

    private static ThreadLocal<Map<String,String>> gettestData = new ThreadLocal<Map<String,String>>();

    public static Map<String, String> readJson(String fileName)
    {
        JSONParser parser = new JSONParser();
        Map<String, String> map = new TreeMap<String, String>();
        try {
            Object obj = null;
            try {
                obj = parser.parse(new FileReader((fileName)));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject jsonObject =(JSONObject)obj;
            //@SuppressWarnings("unchecked")
            Iterator<String> keysItr = jsonObject.keySet().iterator();
            while(keysItr.hasNext()) {
                String key = (String)keysItr.next();
                String value = jsonObject.get(key).toString();
                map.put(key, value);
            }

            System.out.println("Map Value is " + map);

        }  catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void initTestdata(String filename)
    {
        //obj = readJson();
        //gettestData.set(readJson("E:/Automation Pack/CucumberFramework_Demo/src/test/resources/DomainData/" + filename +".json"));
        gettestData.set(readJson(FileReaderHelper.getInstance().getConfigFileHelper().getDomainDataResourcePath() + filename +".json"));
        //testData.set(obj);
    }

    public static Map<String,String> getCurrentTestData(){
        return  gettestData.get();
    }

    private static ThreadLocal<Map<String, String>> testDataMap = new ThreadLocal<Map<String, String>>();

    public static void init(Map<String, String> configMap) {
        JsonDataHelper.testDataMap.set(configMap);
    }

    public static String getConfig(String key) {
        return JsonDataHelper.testDataMap.get().get(key);
    }

    public static void endThreadLocal() {
        testDataMap.remove();

    }

}
