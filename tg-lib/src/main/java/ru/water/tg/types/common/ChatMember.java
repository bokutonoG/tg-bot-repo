package ru.water.tg.types.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.water.tg.types.common.enums.ChatMemberStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ChatMember(
        @JsonProperty(value = "status", required = true) ChatMemberStatus status
) {
}
