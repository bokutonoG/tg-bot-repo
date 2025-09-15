package ru.water.tg.types.send;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InlineKeyboardMarkup(
        @JsonProperty(value = "inline_keyboard", required = true)
        List<List<InlineKeyboardButton>> inlineKeyboard
) {
}
