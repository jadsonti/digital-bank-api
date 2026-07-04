package com.jadson.digitalbank.config;

import com.jadson.digitalbank.domain.Conta;
import com.jadson.digitalbank.repository.ContaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataLoader {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Bean
    CommandLineRunner carregarContasExemplo(ContaRepository contaRepository) {
        return args -> {
            if (contaRepository.count() > 0) {
                return;
            }
            contaRepository.save(new Conta("Jadson Silva", new BigDecimal("5000.00")));
            contaRepository.save(new Conta("Maria Souza", new BigDecimal("3200.50")));
            contaRepository.save(new Conta("Carlos Lima", new BigDecimal("150.00")));
            log.info("Contas de exemplo carregadas com sucesso");
        };
    }
}
