package com.jadson.digitalbank.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal saldo;

    @Version
    private Long versao;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    protected Conta() {
    }

    public Conta(String nome, BigDecimal saldo) {
        this.nome = nome;
        this.saldo = saldo;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public Long getVersao() {
        return versao;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void debitar(BigDecimal valor) {
        if (valor == null || valor.signum() <= 0) {
            throw new IllegalArgumentException("Valor de débito inválido");
        }
        if (saldo.compareTo(valor) < 0) {
            throw new IllegalStateException("Saldo insuficiente");
        }
        saldo = saldo.subtract(valor);
    }

    public void creditar(BigDecimal valor) {
        if (valor == null || valor.signum() <= 0) {
            throw new IllegalArgumentException("Valor de crédito inválido");
        }
        saldo = saldo.add(valor);
    }
}
