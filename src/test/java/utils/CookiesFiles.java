package utils;

import org.openqa.selenium.Cookie;
import tests.BaseTest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

public class CookiesFiles {

    public void fileWrite() {
        File file = new File("Cookies.data");
        try {
            FileWriter fileWrite = new FileWriter(file);
            for (Cookie cookie : BaseTest.driver.manage().getCookies()) {
                fileWrite.write((cookie.getName() + " = " + cookie.getValue())+ "\n");
            }
            fileWrite.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void fileRead(String URL) {
        try {
            File fileDone = new File("Cookies.data");
            FileReader fileReader = new FileReader(fileDone);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, " = ");
                while (tokenizer.hasMoreTokens()) {
                    String name = tokenizer.nextToken();
                    String value = tokenizer.nextToken();
                    Cookie cookie = new Cookie(name, value);
                    BaseTest.driver.manage().addCookie(cookie);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        BaseTest.driver.get(URL);
    }
}
