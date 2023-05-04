package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import tests.BaseTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

public class CookiesUtils {

    File file = new File("Cookies.data");

    public CookiesUtils(WebDriver driver) {
    }

    @Step("Записываем Cookies в файл")
    public void writeCookieToFile() {
        try {
            FileWriter fileWrite = new FileWriter(file);
            for (Cookie cookie : BaseTest.driver.get().manage().getCookies()) {
                fileWrite.write((cookie.getName() + " = " + cookie.getValue()) + "\n");
            }
            fileWrite.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Step("Читаем Cookies из файла")
    public void readCookieFromFile() {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, " = ");
                while (tokenizer.hasMoreTokens()) {
                    String name = tokenizer.nextToken();
                    String value = tokenizer.nextToken();
                    Cookie cookie = new Cookie(name, value);
                    BaseTest.driver.get().manage().addCookie(cookie);
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Step("Удаляем файл с Cookies")
    public void deleteCookiesFile() {
        if (file.isFile()) {
            file.delete();
        }
    }
}
