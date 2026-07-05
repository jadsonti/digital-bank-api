package com.jadson.digitalbank.service;

import com.jadson.digitalbank.domain.Conta;
import com.jadson.digitalbank.domain.Movimentacao;
import com.jadson.digitalbank.dto.TransferenciaRequest;
import com.jadson.digitalbank.exception.BusinessException;
import com.jadson.digitalbank.exception.ResourceNotFoundException;
import com.jadson.digitalbank.repository.ContaRepository;
import com.jadson.digitalbank.repository.MovimentacaoRepository;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferenciaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private MovimentacaoRepository movimentacaoRepository;

    @Mock
    private NotificacaoService notificacaoService;

    @InjectMocks
    private TransferenciaService transferenciaService;

    private Conta origem;
    private Conta destino;

    @BeforeEach
    void setUp() {
        origem = new Conta("Origem", new BigDecimal("1000.00"));
        destino = new Conta("Destino", new BigDecimal("200.00"));
        definirId(origem, 1L);
        definirId(destino, 2L);
    }

    @Test
    void deveTransferirEntreContas() {
        when(contaRepository.buscarPorIdParaAtualizacao(1L)).thenReturn(Optional.of(origem));
        when(contaRepository.buscarPorIdParaAtualizacao(2L)).thenReturn(Optional.of(destino));

        var resposta = transferenciaService.transferir(new TransferenciaRequest(1L, 2L, new BigDecimal("150.00")));

        assertEquals(new BigDecimal("850.00"), resposta.saldoContaOrigem());
        assertEquals(new BigDecimal("350.00"), resposta.saldoContaDestino());
        verify(movimentacaoRepository, times(2)).save(any(Movimentacao.class));
        verify(notificacaoService).notificarTransferenciaConcluida(origem, destino, new BigDecimal("150.00"), resposta.idTransferencia());
    }

    @Test
    void deveRejeitarTransferenciaMesmaConta() {
        assertThrows(BusinessException.class,
                () -> transferenciaService.transferir(new TransferenciaRequest(1L, 1L, new BigDecimal("10.00"))));

        verifyNoInteractions(notificacaoService);
    }

    @Test
    void deveRejeitarSaldoInsuficiente() {
        when(contaRepository.buscarPorIdParaAtualizacao(1L)).thenReturn(Optional.of(origem));
        when(contaRepository.buscarPorIdParaAtualizacao(2L)).thenReturn(Optional.of(destino));

        assertThrows(BusinessException.class,
                () -> transferenciaService.transferir(new TransferenciaRequest(1L, 2L, new BigDecimal("5000.00"))));

        verifyNoInteractions(notificacaoService);
    }

    @Test
    void naoDeveNotificarQuandoContaNaoEncontrada() {
        when(contaRepository.buscarPorIdParaAtualizacao(1L)).thenReturn(Optional.of(origem));
        when(contaRepository.buscarPorIdParaAtualizacao(2L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> transferenciaService.transferir(new TransferenciaRequest(1L, 2L, new BigDecimal("100.00"))));

        verifyNoInteractions(notificacaoService);
    }

    private void definirId(Conta conta, Long id) {
        try {
            var campo = Conta.class.getDeclaredField("id");
            campo.setAccessible(true);
            campo.set(conta, id);
        } catch (ReflectiveOperationException ex) {
            throw new RuntimeException(ex);
        }
    }
}
