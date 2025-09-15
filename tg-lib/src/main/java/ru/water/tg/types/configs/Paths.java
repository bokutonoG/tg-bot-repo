package ru.water.tg.types.configs;

import jakarta.validation.constraints.NotBlank;

public record Paths(@NotBlank String getUpdatesPath,
                    @NotBlank String sendMessagePath,
                    @NotBlank String editMessagePath,
                    @NotBlank String answerCallbackQuery,
                    @NotBlank String getChatMember) {
}
