package utils;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtils {

    public static String valueProperties(String param) {
        try {
            Properties props = new Properties();
            props.load(new InputStreamReader(new FileInputStream("src/main/resources/aplication.properties"), "UTF-8"));
            return props.getProperty(param);
        }
        catch (Exception e) {
        }
        return param;
    }
}
