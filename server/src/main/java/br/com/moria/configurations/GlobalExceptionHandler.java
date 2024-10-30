package br.com.moria.configurations;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> String.format("Campo '%s': %s", error.getField(), error.getDefaultMessage()))
            .collect(Collectors.toList());

        String errorMessage = "Houve erros de validação. Verifique os seguintes campos:";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            Map.of("code", HttpStatus.BAD_REQUEST.value(), "message", errorMessage, "errors", errors));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleConversionExceptions(HttpMessageNotReadableException ex) {
        String message = "Ocorreu um erro de conversão de dados. Por favor, verifique se todos os campos estão corretamente formatados.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            Map.of("code", HttpStatus.BAD_REQUEST.value(), "message", message));
    }
}