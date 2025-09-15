package ru.water.tg.exception;

public class TelegramRequestException extends RuntimeException {
    public TelegramRequestException(String message, Throwable cause) {
        super(message);
    }
}
