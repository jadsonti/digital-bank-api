package com.jadson.digitalbank.service;

import com.jadson.digitalbank.domain.Conta;
import com.jadson.digitalbank.dto.CriarContaRequest;
import com.jadson.digitalbank.exception.ResourceNotFoundException;
import com.jadson.digitalbank.repository.ContaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ContaService contaService;

    @Test
    void deveCriarConta() {
        when(contaRepository.save(any(Conta.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var resposta = contaService.criar(new CriarContaRequest("Ana", new BigDecimal("100.00")));

        assertEquals("Ana", resposta.nome());
        assertEquals(new BigDecimal("100.00"), resposta.saldo());
        verify(contaRepository).save(any(Conta.class));
    }

    @Test
    void deveLancarQuandoContaNaoEncontrada() {
        when(contaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> contaService.buscarPorId(99L));
    }
}
