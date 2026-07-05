package com.jadson.digitalbank.service;

import com.jadson.digitalbank.domain.Conta;
import com.jadson.digitalbank.domain.Notificacao;
import com.jadson.digitalbank.repository.NotificacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class NotificacaoServiceImpl implements NotificacaoService {

    private static final Logger log = LoggerFactory.getLogger(NotificacaoServiceImpl.class);

    private final NotificacaoRepository notificacaoRepository;

    public NotificacaoServiceImpl(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    @Override
    public void notificarTransferenciaConcluida(Conta origem, Conta destino, BigDecimal valor, UUID idTransferencia) {
        String mensagemDebito = String.format(
                "Transferência enviada de R$ %s para conta %d (%s).",
                valor, destino.getId(), destino.getNome());
        String mensagemCredito = String.format(
                "Transferência recebida de R$ %s da conta %d (%s).",
                valor, origem.getId(), origem.getNome());

        notificacaoRepository.save(new Notificacao(origem.getId(), mensagemDebito, idTransferencia, valor));
        notificacaoRepository.save(new Notificacao(destino.getId(), mensagemCredito, idTransferencia, valor));

        log.info("Notificação enviada para conta {} e conta {}", origem.getId(), destino.getId());
    }
}
