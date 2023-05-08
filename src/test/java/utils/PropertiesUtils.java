package utils;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Класс утилит для работы со значениями из файла.
 */
public final class PropertiesUtils {

    private PropertiesUtils() {
    }

    /**
     * Метод извлечения значения по ключу, из файла.
     *
     * @param param Ключ значения
     * @return Название значения
     */
    public static String valueProperties(final String param) {
        try {
            Properties props = new Properties();
            props.load(new InputStreamReader(new FileInputStream(
                    "src/main/resources/aplication.properties"),
                    StandardCharsets.UTF_8));
            return props.getProperty(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return param;
    }
}
