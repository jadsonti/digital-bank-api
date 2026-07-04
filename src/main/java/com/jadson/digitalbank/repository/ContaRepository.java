package com.jadson.digitalbank.repository;

import com.jadson.digitalbank.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
