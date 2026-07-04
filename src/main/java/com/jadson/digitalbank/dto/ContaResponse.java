package com.jadson.digitalbank.dto;

import com.jadson.digitalbank.domain.Conta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ContaResponse(
        Long id,
        String nome,
        BigDecimal saldo,
        LocalDateTime createdAt
) {
    public static ContaResponse de(Conta conta) {
        return new ContaResponse(
                conta.getId(),
                conta.getNome(),
                conta.getSaldo(),
                conta.getCreatedAt()
        );
    }
}
