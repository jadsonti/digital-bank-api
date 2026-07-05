package com.jadson.digitalbank.service;

import com.jadson.digitalbank.domain.Conta;

import java.math.BigDecimal;
import java.util.UUID;

public interface NotificacaoService {

    void notificarTransferenciaConcluida(Conta origem, Conta destino, BigDecimal valor, UUID idTransferencia);
}
