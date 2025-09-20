package ru.water.telegram.bot.dto;

public record UserView(
        long telegramId,
        boolean isSubscribed,
        boolean isPremium
) {
}
