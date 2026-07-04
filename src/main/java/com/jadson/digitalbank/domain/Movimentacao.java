package com.jadson.digitalbank.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "movimentacoes")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID idTransferencia;

    @Column(nullable = false)
    private Long contaId;

    @Column(nullable = false)
    private Long contaContraparteId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimentacao type;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    protected Movimentacao() {
    }

    public Movimentacao(
            UUID idTransferencia,
            Long contaId,
            Long contaContraparteId,
            TipoMovimentacao type,
            BigDecimal valor
    ) {
        this.idTransferencia = idTransferencia;
        this.contaId = contaId;
        this.contaContraparteId = contaContraparteId;
        this.type = type;
        this.valor = valor;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public UUID getIdTransferencia() {
        return idTransferencia;
    }

    public Long getContaId() {
        return contaId;
    }

    public Long getContaContraparteId() {
        return contaContraparteId;
    }

    public TipoMovimentacao getType() {
        return type;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
