package com.jadson.digitalbank.service;

import com.jadson.digitalbank.domain.Conta;
import com.jadson.digitalbank.domain.Movimentacao;
import com.jadson.digitalbank.domain.TipoMovimentacao;
import com.jadson.digitalbank.dto.TransferenciaRequest;
import com.jadson.digitalbank.dto.TransferenciaResponse;
import com.jadson.digitalbank.exception.BusinessException;
import com.jadson.digitalbank.exception.ResourceNotFoundException;
import com.jadson.digitalbank.repository.ContaRepository;
import com.jadson.digitalbank.repository.MovimentacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransferenciaService {

    private final ContaRepository contaRepository;
    private final MovimentacaoRepository movimentacaoRepository;

    public TransferenciaService(
            ContaRepository contaRepository,
            MovimentacaoRepository movimentacaoRepository
    ) {
        this.contaRepository = contaRepository;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    @Transactional
    public TransferenciaResponse transferir(TransferenciaRequest requisicao) {
        if (requisicao.contaOrigemId().equals(requisicao.contaDestinoId())) {
            throw new BusinessException("Conta de origem e destino devem ser diferentes");
        }

        Long primeiroId = Math.min(requisicao.contaOrigemId(), requisicao.contaDestinoId());
        Long segundoId = Math.max(requisicao.contaOrigemId(), requisicao.contaDestinoId());

        Conta primeira = contaRepository.buscarPorIdParaAtualizacao(primeiroId)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada: " + primeiroId));
        Conta segunda = contaRepository.buscarPorIdParaAtualizacao(segundoId)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada: " + segundoId));

        Conta origem = requisicao.contaOrigemId().equals(primeiroId) ? primeira : segunda;
        Conta destino = requisicao.contaDestinoId().equals(primeiroId) ? primeira : segunda;

        try {
            origem.debitar(requisicao.valor());
        } catch (IllegalStateException ex) {
            throw new BusinessException("Saldo insuficiente na conta " + origem.getId());
        }

        destino.creditar(requisicao.valor());

        UUID idTransferencia = UUID.randomUUID();
        movimentacaoRepository.save(new Movimentacao(
                idTransferencia, origem.getId(), destino.getId(), TipoMovimentacao.DEBITO, requisicao.valor()));
        movimentacaoRepository.save(new Movimentacao(
                idTransferencia, destino.getId(), origem.getId(), TipoMovimentacao.CREDITO, requisicao.valor()));

        return new TransferenciaResponse(
                idTransferencia,
                origem.getId(),
                destino.getId(),
                requisicao.valor(),
                origem.getSaldo(),
                destino.getSaldo(),
                LocalDateTime.now()
        );
    }
}
