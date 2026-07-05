package com.jadson.digitalbank.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notificacoes")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long contaId;

    @Column(nullable = false)
    private String mensagem;

    @Column(nullable = false)
    private UUID idTransferencia;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    protected Notificacao() {
    }

    public Notificacao(Long contaId, String mensagem, UUID idTransferencia, BigDecimal valor) {
        this.contaId = contaId;
        this.mensagem = mensagem;
        this.idTransferencia = idTransferencia;
        this.valor = valor;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getContaId() {
        return contaId;
    }

    public String getMensagem() {
        return mensagem;
    }

    public UUID getIdTransferencia() {
        return idTransferencia;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
