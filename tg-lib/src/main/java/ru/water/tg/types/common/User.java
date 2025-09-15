package ru.water.tg.types.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record User(
        @JsonProperty(value = "id", required = true) long userId,
        @JsonProperty(value = "is_bot", required = true) boolean isBot,
        @JsonProperty(value = "first_name") String firstName,
        @JsonProperty(value = "last_name") String lastName,
        @JsonProperty(value = "username") String username,
        @JsonProperty(value = "language_code") String languageCode

) {
}
