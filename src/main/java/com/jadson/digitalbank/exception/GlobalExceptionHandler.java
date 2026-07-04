package com.jadson.digitalbank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> tratarNaoEncontrado(ResourceNotFoundException ex) {
        return montarResposta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> tratarNegocio(BusinessException ex) {
        return montarResposta(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> tratarArgumentoInvalido(IllegalArgumentException ex) {
        return montarResposta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> campos = new HashMap<>();
        for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
            campos.put(erro.getField(), erro.getDefaultMessage());
        }
        Map<String, Object> corpo = new HashMap<>();
        corpo.put("timestamp", LocalDateTime.now());
        corpo.put("status", HttpStatus.BAD_REQUEST.value());
        corpo.put("erro", "Falha na validação");
        corpo.put("campos", campos);
        return ResponseEntity.badRequest().body(corpo);
    }

    private ResponseEntity<Map<String, Object>> montarResposta(HttpStatus status, String mensagem) {
        Map<String, Object> corpo = new HashMap<>();
        corpo.put("timestamp", LocalDateTime.now());
        corpo.put("status", status.value());
        corpo.put("erro", mensagem);
        return ResponseEntity.status(status).body(corpo);
    }
}
