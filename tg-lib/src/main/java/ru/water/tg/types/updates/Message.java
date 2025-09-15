package ru.water.tg.types.updates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.water.tg.types.common.Chat;
import ru.water.tg.types.common.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Message(
        @JsonProperty(value = "message_id", required = true) int messageId,
        @JsonProperty(value = "chat", required = true) Chat chat,
        @JsonProperty(value = "from") User user,
        @JsonProperty(value = "date") int date,
        @JsonProperty(value = "text", required = true) String text
        ) {
}
