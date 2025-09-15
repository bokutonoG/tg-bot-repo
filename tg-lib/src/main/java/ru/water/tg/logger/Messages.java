package ru.water.tg.logger;

public enum Messages {

    SEND_REQUEST_TO_TG("Отправляем запрос в телеграмм, {}"),
    RESPONSE_RECEIVED("Получен ответ от телеграмм: {}"),
    ERROR_RECEIVED("Получена ошибка от телеграмм: {}"),

    CHECK_TG_RESPONSE("Проверяем ответ от телеграмма"),

    BASE_TG_CHECK_ERROR("Телеграмм ответ не прошел проверку"),
    INVALID_RESPONSE("Получен некорректный ответ: code {}, description {}"),
    EMPTY_RESPONSE("Получен ответ без апдейтов, {}"),
    NULL_RESPONSE("Получен пустой ответ"),
    ;


    public final String message;

    Messages(String message) {
        this.message = message;
    }
}
