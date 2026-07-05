package com.jadson.digitalbank.service;

import com.jadson.digitalbank.dto.NotificacaoResponse;
import com.jadson.digitalbank.repository.NotificacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificacaoQueryService {

    private final NotificacaoRepository notificacaoRepository;
    private final ContaService contaService;

    public NotificacaoQueryService(NotificacaoRepository notificacaoRepository, ContaService contaService) {
        this.notificacaoRepository = notificacaoRepository;
        this.contaService = contaService;
    }

    @Transactional(readOnly = true)
    public List<NotificacaoResponse> buscarPorContaId(Long contaId) {
        contaService.obterConta(contaId);
        return notificacaoRepository.findByContaIdOrderByCreatedAtDesc(contaId).stream()
                .map(NotificacaoResponse::de)
                .toList();
    }
}
