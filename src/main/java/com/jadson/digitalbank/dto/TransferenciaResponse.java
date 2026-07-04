package com.jadson.digitalbank.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransferenciaResponse(
        UUID idTransferencia,
        Long contaOrigemId,
        Long contaDestinoId,
        BigDecimal valor,
        BigDecimal saldoContaOrigem,
        BigDecimal saldoContaDestino,
        LocalDateTime createdAt
) {
}
