package com.jadson.digitalbank.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferenciaRequest(
        @NotNull(message = "Conta de origem é obrigatória")
        Long contaOrigemId,

        @NotNull(message = "Conta de destino é obrigatória")
        Long contaDestinoId,

        @NotNull(message = "Valor é obrigatório")
        @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
        BigDecimal valor
) {
}
