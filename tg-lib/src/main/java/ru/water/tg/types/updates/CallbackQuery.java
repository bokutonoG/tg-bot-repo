package ru.water.tg.types.updates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.water.tg.types.common.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CallbackQuery(
        @JsonProperty(value = "id", required = true) String id,
        @JsonProperty(value = "from", required = true) User user,
        @JsonProperty(value = "message") Message message,
        @JsonProperty(value = "data") String data
) {
}
