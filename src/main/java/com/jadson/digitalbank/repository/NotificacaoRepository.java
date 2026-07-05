package com.jadson.digitalbank.repository;

import com.jadson.digitalbank.domain.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    List<Notificacao> findByContaIdOrderByCreatedAtDesc(Long contaId);
}
