package org.teamory.backend.Exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        if (ex.getRequiredType() == UUID.class) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid UUID format: " + ex.getValue());
            return ResponseEntity.badRequest().body(error);
        }
        throw ex;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFound(EntityNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();

        if (cause instanceof InvalidFormatException invalidFormatException) {
            // Check if it's an enum deserialization error
            if (invalidFormatException.getTargetType().isEnum()) {
                String invalidValue = invalidFormatException.getValue().toString();
                String enumValues = String.join(", ",
                        java.util.Arrays.stream(invalidFormatException.getTargetType().getEnumConstants())
                                .map(Object::toString)
                                .toList()
                );

                String message = String.format(
                        "Invalid value '%s' for enum %s. Allowed values are: [%s]",
                        invalidValue,
                        invalidFormatException.getTargetType().getSimpleName(),
                        enumValues
                );

                return ResponseEntity.badRequest().body(message);
            }
        }

        // Fallback generic error message
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Malformed JSON request: " + ex.getMessage());
    }

}



