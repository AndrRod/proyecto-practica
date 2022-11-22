package com.proyect.shiftsystem.excepcion;

import com.proyect.shiftsystem.message.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ControllerAdvice
public class ExcepHandler {
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<MessageResponse> notFoundException(NotFoundException exp, HttpServletRequest request){
        return ResponseEntity.status(404).body(new MessageResponse(exp.getMessage(), 404, request.getRequestURI()));
    }
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<MessageResponse> notFoundException(BadRequestException exp, HttpServletRequest request){
        return ResponseEntity.status(400).body(new MessageResponse(exp.getMessage(), 400, request.getRequestURI()));
    }
}
