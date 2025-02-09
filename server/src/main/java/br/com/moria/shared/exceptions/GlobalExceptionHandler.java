package br.com.moria.shared.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import io.jsonwebtoken.io.IOException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    @Autowired
    private MessageSource messageSource;

    /**
     * Handle validation exceptions from MethodArgumentNotValidException (400 Bad Request)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", System.currentTimeMillis());
        response.put("status", HttpStatus.BAD_REQUEST.value());

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String message = messageSource.getMessage(
                    error,
                    Locale.getDefault()
            );
            errors.put(error.getField(), message);
        }
        response.put("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Handle validation exceptions from ConstraintViolationException (400 Bad Request)
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations().stream()
            .map(violation -> String.format("Campo '%s': %s", violation.getPropertyPath(), violation.getMessage()))
            .collect(Collectors.toList());

        String errorMessage = "Houve erros de validação. Verifique os seguintes campos:";
        logAndAdaptMessage(ex, errorMessage, HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            Map.of("code", HttpStatus.BAD_REQUEST.value(), "message", errorMessage, "errors", errors));
    }

    /**
     * Handle data conversion exceptions (400 Bad Request)
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleConversionExceptions(HttpMessageNotReadableException ex) {
        String message = ex.getMessage() != null ? ex.getMessage()
            : "Ocorreu um erro de conversão de dados. Verifique se todos os campos estão corretamente formatados.";
        logAndAdaptMessage(ex, message, HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            Map.of("code", HttpStatus.BAD_REQUEST.value(), "message", message));
    }

    /**
     * Handle illegal arguments (400 Bad Request)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        String message = ex.getMessage() != null ? ex.getMessage() 
            : "Argumento inválido. Verifique se todos os campos estão corretamente preenchidos.";
        logAndAdaptMessage(ex, message, HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            Map.of("code", HttpStatus.BAD_REQUEST.value(), "message", message));
    }

    /**
     * Handle authentication exceptions (401 Unauthorized)
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        String message = ex.getMessage() != null ? ex.getMessage()
            :  "Acesso não autorizado: Token JWT inválido ou não fornecido."; 
        logAndAdaptMessage(ex, message, HttpStatus.UNAUTHORIZED);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
            Map.of("code", HttpStatus.UNAUTHORIZED.value(), "message", message));
    }

    /**
     * Handle access denied exceptions (403 Forbidden)
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        String message = ex.getMessage() != null ? ex.getMessage()
            : "Você não tem permissão para acessar este recurso.";
        logAndAdaptMessage(ex, message, HttpStatus.FORBIDDEN);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            Map.of("code", HttpStatus.FORBIDDEN.value(), "message", message));
    }

    /**
     * Handle "resource not found" exceptions (404 Not Found)
     */
    @ExceptionHandler({ NoHandlerFoundException.class, EntityNotFoundException.class })
    public ResponseEntity<Object> handleNotFoundException(Exception ex) {
        String message = ex.getMessage() != null ? ex.getMessage() : "Recurso não encontrado.";
        logAndAdaptMessage(ex, message, HttpStatus.NOT_FOUND);
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            Map.of("code", HttpStatus.NOT_FOUND.value(), "message", message));
    }

    /**
     * Handle data integrity violations, such as unique constraint violations (409 Conflict)
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String message = "Erro de integridade de dados: verifique se o recurso já existe ou se há uma duplicação.";
        logAndAdaptMessage(ex, message, HttpStatus.CONFLICT);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
            Map.of("code", HttpStatus.CONFLICT.value(), "message", message));
    }

    /**
     * Handle file upload size limit exceeded exception (413 Payload Too Large)
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        String message = "O arquivo enviado excede o tamanho máximo permitido.";
        logAndAdaptMessage(ex, message, HttpStatus.PAYLOAD_TOO_LARGE);

        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(
            Map.of("code", HttpStatus.PAYLOAD_TOO_LARGE.value(), "message", message));
    }

    /**
     * Handle I/O exceptions (500 Internal Server Error)
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> handleIOException(IOException ex) {
        String message = "Erro ao processar o arquivo.";
        logAndAdaptMessage(ex, message, HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            Map.of("code", HttpStatus.INTERNAL_SERVER_ERROR.value(), "message", message));
    }

    /**
     * Handle generic exceptions (500 Internal Server Error)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        String message = "Ocorreu um erro interno no servidor. Por favor, tente novamente mais tarde.";
        logAndAdaptMessage(ex, message, HttpStatus.INTERNAL_SERVER_ERROR);
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            Map.of("code", HttpStatus.INTERNAL_SERVER_ERROR.value(), "message", message));
    }

    /**
     * Utility method to log exceptions and adapt messages based on environment
     */
    private void logAndAdaptMessage(Exception ex, String userMessage, HttpStatus status) {
        if ("dev".equals(activeProfile))
            logger.error("Erro [{}]: {}", status, ex.getMessage(), ex);
        else
            logger.error("Erro [{}]: {}", status, userMessage);
    }
}