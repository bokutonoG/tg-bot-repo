package ru.water.tg.types.send;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SendMessageRequest(
        @JsonProperty(value = "chat_id", required = true) long chatId,
        @JsonProperty(value = "text", required = true) String text,
        @JsonProperty(value = "reply_markup") InlineKeyboardMarkup replyMarkup
) {

    public SendMessageRequest {
        if(text == null || text.isEmpty())
            throw new IllegalArgumentException("text cannot be null or empty");
    }
}
