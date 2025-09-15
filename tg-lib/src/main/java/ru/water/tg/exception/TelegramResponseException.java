package ru.water.tg.exception;

public class TelegramResponseException extends RuntimeException {
    public TelegramResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public TelegramResponseException(String message) {
        super(message);
    }
}
