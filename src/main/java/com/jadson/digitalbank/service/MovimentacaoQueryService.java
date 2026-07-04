package com.jadson.digitalbank.service;

import com.jadson.digitalbank.dto.MovimentacaoResponse;
import com.jadson.digitalbank.repository.MovimentacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovimentacaoQueryService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final ContaService contaService;

    public MovimentacaoQueryService(MovimentacaoRepository movimentacaoRepository, ContaService contaService) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.contaService = contaService;
    }

    @Transactional(readOnly = true)
    public List<MovimentacaoResponse> buscarPorContaId(Long contaId) {
        contaService.obterConta(contaId);
        return movimentacaoRepository.findByContaIdOrderByCreatedAtDesc(contaId).stream()
                .map(MovimentacaoResponse::de)
                .toList();
    }
}
