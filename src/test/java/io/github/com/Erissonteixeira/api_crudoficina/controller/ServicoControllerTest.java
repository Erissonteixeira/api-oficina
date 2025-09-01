package io.github.com.Erissonteixeira.api_crudoficina.controller;

import io.github.com.Erissonteixeira.api_crudoficina.dto.ServicoRequestDTO;
import io.github.com.Erissonteixeira.api_crudoficina.dto.ServicoResponseDTO;
import io.github.com.Erissonteixeira.api_crudoficina.service.ServicoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServicoControllerTest {
    @InjectMocks
    private ServicoController controller;

    @Mock
    private ServicoService service;

    @Test
    @DisplayName("Deve criar um serviço com sucesso e retornar status code 200")
    void shouldCreateServiceSuccessfullyAndReturnStatus200(){
        ServicoRequestDTO servicoRequestDTO = new ServicoRequestDTO("Troca de óleo", new BigDecimal("150.50"), "ATIVO");
        ServicoResponseDTO servicoResponseDTO = new ServicoResponseDTO(1L, "Troca de óleo", new BigDecimal("150.50"), "ATIVO");

        when(service.criar(servicoRequestDTO)).thenReturn(servicoResponseDTO);

        ResponseEntity<ServicoResponseDTO> response = controller.criar(servicoRequestDTO);

        assertEquals(servicoResponseDTO, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verifyNoMoreInteractions(service);
    }
}
