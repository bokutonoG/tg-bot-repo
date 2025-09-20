package ru.water.telegram.bot.logger;

public enum Messages {

    COMMIT_UPDATE_INFO("Сдвигаем оффсет, последнее прочитанное сообщение из пачки с update_id={}"),
    EMPTY_UPDATES("Пустая пачка апдейтов от Telegram (новых событий нет)"),
    ERROR_WHILE_POLLING("Ошибка при выполнении цикла polling: {}")
    ;


    Messages(String message) {
        this.message = message;
    }

    public final String message;
}
