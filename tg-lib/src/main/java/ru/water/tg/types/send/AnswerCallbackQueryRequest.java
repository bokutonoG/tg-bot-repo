package ru.water.tg.types.send;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record AnswerCallbackQueryRequest(
        @JsonProperty(value = "callback_query_id", required = true) String callbackQueryId,
        @JsonProperty(value = "text") String text
) {
    public AnswerCallbackQueryRequest {
        if(callbackQueryId == null || callbackQueryId.isBlank()) {
            throw new IllegalArgumentException("callbackQueryId cannot be null or empty");
        }
    }
}
