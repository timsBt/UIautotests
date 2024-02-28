package utils;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Класс утилит для перезапуска теста после падения.
 */
public class RetryClassUtils implements IRetryAnalyzer {

    /**
     * Объявление переменной.
     */
    private int retryCount = 0;

    /**
     * Объявление переменной (количество перезапусков).
     */
    private static final int MAX_RETRY_COUNT = 2;

    /**
     * Метод повторного перезапуска теста.
     *
     * @param result результат теста
     * @return true - если провальный тест, false - если успешный
     */
    @Override
    public boolean retry(final ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            return true;
        }
        return false;
    }
}

//bp Z1



