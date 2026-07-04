package com.jadson.digitalbank.controller;

import com.jadson.digitalbank.dto.ContaResponse;
import com.jadson.digitalbank.dto.CriarContaRequest;
import com.jadson.digitalbank.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContaResponse criar(@Valid @RequestBody CriarContaRequest requisicao) {
        return contaService.criar(requisicao);
    }

    @GetMapping
    public List<ContaResponse> listar() {
        return contaService.listar();
    }

    @GetMapping("/{id}")
    public ContaResponse buscarPorId(@PathVariable Long id) {
        return contaService.buscarPorId(id);
    }
}
