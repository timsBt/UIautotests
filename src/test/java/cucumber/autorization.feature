# language: ru

Функция: Авторизация на сайте

  Предыстория:
    Допустим пользователь открывает страницу авторизации

  Сценарий: Авторизация с корректными данными
    Если Пользователь ввел данные Username "angular", Password "password", Username2 "Tims"
    И нажал кнопку Login
    Тогда Авторизация пользователя прошла успешно

  Структура сценария: Авторизация с некорректными данными
    Если Пользователь ввел некорректные данные "<Username>", "<Password>", "<Username2>"
    И нажал кнопку Login
    Тогда Появилось сообщение об ошибке "Username or password is incorrect"
    Примеры:
      | Username | Password | Username2 |
      | tims     | 2023     | Tims      |
      | angular  | 2023     | Tims      |
      | tims     | password | Tims      |

  Сценарий: Авторизация с пустым полем Username
    Если Пользователь ввел данные Password "password", Username2 "Tims"
    И нажал кнопку Login
    Тогда Появилось сообщение об ошибке в поле Username "You did not enter a username"

  Сценарий: Авторизация с пустым полем Password
    Если Пользователь ввел данные Username "angular", Username2 "Tims"
    И нажал кнопку Login
    Тогда Появилось сообщение об ошибке в поле Password "You did not enter a username"

  Сценарий: Авторизация с одним символом в поле Username
    Если Пользователь ввел данные Username "A", Password "password", Username2 "Tims"
    И нажал кнопку Login
    Тогда Появилось сообщение об ошибке под полем Username "Your username must be between 3 and 50 characters long"

  Сценарий: Авторизация с одним символом в поле Password
    Если Пользователь ввел данные Username "angular", Password "A", Username2 "Tims"
    И нажал кнопку Login
    Тогда Появилось сообщение об ошибке под полем Password "Your username must be between 3 and 100 characters long"
