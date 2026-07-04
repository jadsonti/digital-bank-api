package com.jadson.digitalbank.repository;

import com.jadson.digitalbank.domain.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    List<Movimentacao> findByContaIdOrderByCreatedAtDesc(Long contaId);
}
