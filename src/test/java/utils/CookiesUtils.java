package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

/**
 * Класс утилит для работы с Cookies.
 */
public class CookiesUtils {

    /**
     * Объявление экземпляра файла.
     */
    private final File file = new File("Cookies.data");

    /**
     * Объявление экземпляра драйвера.
     */
    private final WebDriver driver;

    /**
     * Конструктор класса для драйвера.
     *
     * @param webDriver драйвер для управления
     */
    public CookiesUtils(final WebDriver webDriver) {
        this.driver = webDriver;
    }

    /**
     * Метод записи Cookies в файл.
     */
    @Step("Записываем Cookies в файл")
    public void writeCookieToFile() {
        try {
            FileWriter fileWrite = new FileWriter(file);
            for (Cookie cookie : driver.manage().getCookies()) {
                fileWrite.write((cookie.getName() + ";"
                        + cookie.getValue()) + "\n");
            }
            fileWrite.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Метод чтения Cookies из файла.
     */
    @Step("Читаем Cookies из файла")
    public void readCookieFromFile() {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ";");
                while (tokenizer.hasMoreTokens()) {
                    String name = tokenizer.nextToken();
                    String value = tokenizer.nextToken();
                    Cookie cookie = new Cookie(name, value);
                    driver.manage().addCookie(cookie);
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Метод удаления файла с Cookies.
     */
    @Step("Удаляем файл с Cookies")
    public void deleteCookiesFile() {
        if (file.isFile()) {
            file.delete();
        }
    }
}
