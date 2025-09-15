package ru.water.tg.http;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResponse<T>(
        @JsonProperty(value = "ok", required = true) boolean ok,
        @JsonProperty(value = "result") T result,
        @JsonProperty(value = "error_code") Integer errorCode,
        @JsonProperty(value = "description") String description
) {
}
