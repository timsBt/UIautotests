package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import tests.BaseTest;

/**
 * Класс утилит для работы с JavascriptExecutor.
 */
public class ExecutorUtils {

    private JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) BaseTest.getDriver();
    }

    /**
     * Метод для отвода фокуса с поля ввода Username.
     */
    @Step("Отводим фокус с поля ввода Username")
    public void shiftFocusFromUsernameField() {
        getJavascriptExecutor()
                .executeScript("document.getElementById('username').blur();");
    }

    /**
     * Метод для проверки отсутствия вертикального скролла на странице.
     *
     * @return false если отсутсвует скролл, true если присутствует
     */
    @Step("Проверка отсутствия вертикального скролла на странице по скрипту")
    public boolean executeScriptHeight() {
        String scriptHeight = "return document.documentElement."
                + "scrollHeight>document.documentElement.clientHeight";
        return (boolean) getJavascriptExecutor().executeScript(scriptHeight);
    }

    /**
     * Метод для проверки отсутствия горизонтального скролла на странице.
     *
     * @return false если отсутсвует скролл, true если присутствует
     */
    @Step("Проверка отсутствия горизонтального скролла на странице по скрипту")
    public boolean executeScriptWidth() {
        String scriptWidth = "return document.documentElement."
                + "scrollWidth>document.documentElement.clientWidth";
        return (boolean) getJavascriptExecutor().executeScript(scriptWidth);
    }
}
