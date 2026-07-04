package com.jadson.digitalbank.controller;

import com.jadson.digitalbank.dto.ContaResponse;
import com.jadson.digitalbank.dto.CriarContaRequest;
import com.jadson.digitalbank.dto.MovimentacaoResponse;
import com.jadson.digitalbank.service.ContaService;
import com.jadson.digitalbank.service.MovimentacaoQueryService;
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
    private final MovimentacaoQueryService movimentacaoQueryService;

    public ContaController(ContaService contaService, MovimentacaoQueryService movimentacaoQueryService) {
        this.contaService = contaService;
        this.movimentacaoQueryService = movimentacaoQueryService;
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

    @GetMapping("/{id}/movimentacoes")
    public List<MovimentacaoResponse> movimentacoes(@PathVariable Long id) {
        return movimentacaoQueryService.buscarPorContaId(id);
    }
}
