package ru.water.tg.types.send;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.nio.charset.StandardCharsets;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public record InlineKeyboardButton(
        @JsonProperty(value = "text", required = true) String text,
        @JsonProperty(value = "callback_data") String callbackData,
        @JsonProperty(value = "url") String url
) {

    public InlineKeyboardButton {
        if(text == null || text.isBlank()) {
            throw new IllegalArgumentException("Поле текст не может быть пустым");
        }
        boolean hasCallback = callbackData != null;
        boolean hasUrl = url != null;
        if (hasCallback == hasUrl) {
            throw new IllegalArgumentException("Взаимоисключающие поля callback_data и url заполненны некорректно");
        }
        if (hasCallback && callbackData.getBytes(StandardCharsets.UTF_8).length > 64) {
            throw new IllegalArgumentException("callback_data превышает возможное значение");
        }

        }
    }

