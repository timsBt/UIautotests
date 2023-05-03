package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import tests.BaseTest;

public class ExecutorUtils {

    private String scriptHeight = "return document.documentElement.scrollHeight>document.documentElement.clientHeight";
    private String scriptWidth = "return document.documentElement.scrollWidth>document.documentElement.clientWidth";

    private JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) BaseTest.driver.get();
    }

    @Step("Отводим фокус с поля ввода Username")
    public Object shiftFocusFromUsernameField() {
        return getJavascriptExecutor().executeScript("document.getElementById('username').blur();");
    }

    @Step("Проверка отсутствия вертикального скролла на странице по скрипту")
    public boolean executeScriptHeight() {
        return (boolean) getJavascriptExecutor().executeScript(scriptHeight);
    }

    @Step("Проверка отсутствия горизонтального скролла на странице по скрипту")
    public boolean executeScriptWidth() {
        return (boolean) getJavascriptExecutor().executeScript(scriptWidth);
    }
}
