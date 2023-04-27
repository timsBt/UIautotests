package pages;

import org.testng.annotations.DataProvider;


import static utils.ValueProperties.valueProperties;

public class CustomDataProvider {

    @DataProvider (name = "LoginDataProvider")
    public Object[][] getData() {
        Object[][] data = { {valueProperties("username"),valueProperties("password"),valueProperties("username2")},
                {valueProperties("username"),valueProperties("password"),valueProperties("username2(1)")},
                {valueProperties("username"),valueProperties("password"),valueProperties("username2(2)")}};
        return data;
    }

    @DataProvider (name = "inValidLoginDataProvider")
    public Object[][] getDataTwo() {
        Object[][] data = {{valueProperties("invalidUsername"),valueProperties("invalidPassword"),valueProperties("username2")},
                {valueProperties("invalidUsername2"),valueProperties("invalidPassword2"),valueProperties("username2(1)")},
                {valueProperties("invalidUsername3"),valueProperties("invalidPassword3"),valueProperties("username2(2)")}};
        return data;
    }

}

