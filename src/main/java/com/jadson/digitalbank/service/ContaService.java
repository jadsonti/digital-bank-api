package com.jadson.digitalbank.service;

import com.jadson.digitalbank.domain.Conta;
import com.jadson.digitalbank.dto.ContaResponse;
import com.jadson.digitalbank.dto.CriarContaRequest;
import com.jadson.digitalbank.exception.ResourceNotFoundException;
import com.jadson.digitalbank.repository.ContaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Transactional
    public ContaResponse criar(CriarContaRequest requisicao) {
        Conta conta = new Conta(requisicao.nome(), requisicao.saldoInicial());
        return ContaResponse.de(contaRepository.save(conta));
    }

    @Transactional(readOnly = true)
    public ContaResponse buscarPorId(Long id) {
        return ContaResponse.de(obterConta(id));
    }

    @Transactional(readOnly = true)
    public List<ContaResponse> listar() {
        return contaRepository.findAll().stream().map(ContaResponse::de).toList();
    }

    Conta obterConta(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada: " + id));
    }
}
