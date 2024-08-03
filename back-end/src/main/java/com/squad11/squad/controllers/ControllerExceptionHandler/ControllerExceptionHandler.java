package com.squad11.squad.controllers.ControllerExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice()
public class ControllerExceptionHandler {

    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<Map<String, String>> ResponseException(ResponseException exGen) {
        Map<String, String> msg = new HashMap<>();
        msg.put(exGen.getCode(), exGen.getMessage());
        return new ResponseEntity<>(msg, exGen.getHttp());
    }


    ///--- Validator --- ///

    // exeptions for validations on forms login and register
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> ValidateError(MethodArgumentNotValidException e) {
        Map<String, String> response = extractMessageErrorsDTO(e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //Extraction of error message, from the validation of the forms.
    private Map<String, String> extractMessageErrorsDTO(BindingResult result) {
        Map<String, String> response = new HashMap<>();
        for (FieldError item : result.getFieldErrors()) {
            response.put(item.getField().toUpperCase(), Objects.requireNonNull(item.getDefaultMessage()).toUpperCase());
        }
        return response;
    }
}
