package com.squad11.squad.controllers.ControllerExceptionHandler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseException extends RuntimeException {
    private final String code;
    private final HttpStatus http;

    public ResponseException(String code, String message, HttpStatus http) {
        super(message);
        this.http = http;
        this.code = code;
    }
}
