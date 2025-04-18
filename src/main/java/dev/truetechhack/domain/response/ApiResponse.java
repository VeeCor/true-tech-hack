package dev.truetechhack.domain.response;

import lombok.Data;

//Entity класс для парсинга ответов от true tabs
@Data
public class ApiResponse<T> {
    private int code;
    private boolean success;
    private String message;
    private T data;
}
