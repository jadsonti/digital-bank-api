package com.jadson.digitalbank.dto;

import com.jadson.digitalbank.domain.Notificacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record NotificacaoResponse(
        Long id,
        Long contaId,
        String mensagem,
        UUID idTransferencia,
        BigDecimal valor,
        LocalDateTime createdAt
) {
    public static NotificacaoResponse de(Notificacao notificacao) {
        return new NotificacaoResponse(
                notificacao.getId(),
                notificacao.getContaId(),
                notificacao.getMensagem(),
                notificacao.getIdTransferencia(),
                notificacao.getValor(),
                notificacao.getCreatedAt()
        );
    }
}
