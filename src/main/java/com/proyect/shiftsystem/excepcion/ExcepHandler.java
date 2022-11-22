package com.proyect.shiftsystem.excepcion;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ExcepHandler {
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Map<String, String>> notFoundException(NotFoundException exp){
        return ResponseEntity.status(404).body(Map.of("Message", exp.getMessage()));
    }
}
