package ru.water.tg.types.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Chat(
        @JsonProperty(value = "id", required = true) long chatId,
        @JsonProperty(value = "type", required = true) String type
) {
}
