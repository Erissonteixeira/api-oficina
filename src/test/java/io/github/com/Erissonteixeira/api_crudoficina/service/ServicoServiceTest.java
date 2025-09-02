package io.github.com.Erissonteixeira.api_crudoficina.service;

import io.github.com.Erissonteixeira.api_crudoficina.dto.ServicoRequestDTO;
import io.github.com.Erissonteixeira.api_crudoficina.model.Servico;
import io.github.com.Erissonteixeira.api_crudoficina.repository.ServicoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class ServicoServiceTest {
    @InjectMocks
    private ServicoService service;

    @Mock
    private ServicoRepository repository;

    @Test
    @DisplayName("Deve salvar um serviço com sucesso")
    void shouldSaveServiceSucessFully(){
        ServicoRequestDTO request = new ServicoRequestDTO("Troca de óleo", new BigDecimal("150.00"), "ATIVO");

        Servico entity = Servico.builder()
                .descricao("Troca de óleo")
                .valor(new BigDecimal("150.00"))
                .status("Ativo")
                .build();
    }


}
