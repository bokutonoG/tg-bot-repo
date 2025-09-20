package ru.water.telegram.bot.telegram;

public enum TelegramMessages {

    INVALID_CHAT_TYPE("Бот принимает команды только в личной переписке"),
    EMPTY_USER("В полученном сообщении отсутствует информация о пользователе"),
    EMPTY_MESSAGE_TEXT("В полученном сообщении отсутствует текст"),
    ONBOARDING_MESSAGE("Вы успешно зарегистрировались в нашем сервисе, для использования сервиса необходимо подписать на канал"),
    UNKNOWN_COMMAND("Неизвестная команда")

    ;

    public final String message;


    TelegramMessages(String message) {
        this.message = message;
    }
}
