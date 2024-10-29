package br.com.moria.configurations;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ MethodArgumentNotValidException.class, ConstraintViolationException.class })
    public ResponseEntity<Object> handleValidationExceptions(Exception ex) {
        String message = "Erro de validação. Verifique os campos obrigatórios.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            Map.of("code", HttpStatus.BAD_REQUEST.value(), "message", message));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleConversionExceptions(HttpMessageNotReadableException ex) {
        String message = "Erro de conversão de dados. Verifique o tipo e valores dos campos.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            Map.of("code", HttpStatus.BAD_REQUEST.value(), "message", message));
    }
}