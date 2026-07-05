package com.jadson.digitalbank.controller;

import com.jadson.digitalbank.dto.NotificacaoResponse;
import com.jadson.digitalbank.service.NotificacaoQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/contas/{contaId}/notificacoes")
public class NotificacaoController {

    private final NotificacaoQueryService notificacaoQueryService;

    public NotificacaoController(NotificacaoQueryService notificacaoQueryService) {
        this.notificacaoQueryService = notificacaoQueryService;
    }

    @GetMapping
    public List<NotificacaoResponse> listar(@PathVariable Long contaId) {
        return notificacaoQueryService.buscarPorContaId(contaId);
    }
}
