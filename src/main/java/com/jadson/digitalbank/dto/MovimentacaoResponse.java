package com.jadson.digitalbank.dto;

import com.jadson.digitalbank.domain.Movimentacao;
import com.jadson.digitalbank.domain.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record MovimentacaoResponse(
        Long id,
        UUID idTransferencia,
        Long contaId,
        Long contaContraparteId,
        TipoMovimentacao type,
        BigDecimal valor,
        LocalDateTime createdAt
) {
    public static MovimentacaoResponse de(Movimentacao movimentacao) {
        return new MovimentacaoResponse(
                movimentacao.getId(),
                movimentacao.getIdTransferencia(),
                movimentacao.getContaId(),
                movimentacao.getContaContraparteId(),
                movimentacao.getType(),
                movimentacao.getValor(),
                movimentacao.getCreatedAt()
        );
    }
}
