package ru.water.tg.types.updates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Update(
        @JsonProperty(value = "update_id") long updateId,
        @JsonProperty(value = "message") Message message,
        @JsonProperty(value = "callback_query") CallbackQuery callbackQuery
) {
}
