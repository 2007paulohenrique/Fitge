package com.fitge.api.util.formatter;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseFormatter<T> {
    private T result;
    private String messageCode;

    private ApiResponseFormatter(T result, String messageCode) {
        this.result = result;
        this.messageCode = messageCode;
    }

    public static <T> ApiResponseFormatter<T> of(T data, String messageCode) {
        return new ApiResponseFormatter<>(data, messageCode);
    }

    public static <T> ApiResponseFormatter<Map<String, List<T>>> ofList(List<T> list, String messageCode) {
        return new ApiResponseFormatter<>(Map.of("list", list), messageCode);
    }

    public T getResult() {
        return result;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
}
