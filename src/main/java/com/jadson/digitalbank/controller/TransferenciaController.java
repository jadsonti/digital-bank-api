package com.jadson.digitalbank.controller;

import com.jadson.digitalbank.dto.TransferenciaRequest;
import com.jadson.digitalbank.dto.TransferenciaResponse;
import com.jadson.digitalbank.service.TransferenciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransferenciaResponse transferir(@Valid @RequestBody TransferenciaRequest requisicao) {
        return transferenciaService.transferir(requisicao);
    }
}
