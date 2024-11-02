package br.com.moria.configurations;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

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

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NoHandlerFoundException ex) {
        String message = "Recurso não encontrado. Verifique a URL e tente novamente.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            Map.of("code", HttpStatus.NOT_FOUND.value(), "message", message));
    }

    /*@ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        String message = "Acesso não autorizado: Token JWT inválido ou não fornecido.";
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
            Map.of("code", HttpStatus.UNAUTHORIZED.value(), "message", message));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        String message = "Você não tem permissão para acessar este recurso.";
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            Map.of("code", HttpStatus.FORBIDDEN.value(), "message", message));
    }*/
}