package ru.water.tg.types.send;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EditMessageTextRequest(
        @JsonProperty(value = "chat_id", required = true) long chatId,
        @JsonProperty(value = "message_id", required = true) int messageId,
        @JsonProperty(value = "text", required = true) String text,
        @JsonProperty(value = "reply_markup") InlineKeyboardMarkup replyMarkup
) {
    public EditMessageTextRequest {
        if(text == null || text.isBlank()) {
            throw new IllegalArgumentException("text cannot be null or empty");
        }
    }
}
