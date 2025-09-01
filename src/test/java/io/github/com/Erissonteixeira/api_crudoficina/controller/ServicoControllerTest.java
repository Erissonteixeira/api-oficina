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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
    @Test
    @DisplayName("Deve listar todos os serviços e retornar status 200")
    void shouldReturnAllServicesAndStatus200(){
        List<ServicoResponseDTO> lista = Arrays.asList(
                new ServicoResponseDTO(1l, "Troca de óleo", new BigDecimal("150.00"), "ATIVO"),
                new ServicoResponseDTO(2L, "Alinhamento", new BigDecimal("200.00"), "ATIVO")
        );

        when(service.listarTodos()).thenReturn(lista);

        ResponseEntity<List<ServicoResponseDTO>> response = controller.listarTodos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(service).listarTodos();
    }
    @Test
    @DisplayName("Deve buscar serviço por id existente e retornar status 200")
    void shouldReturnServiceByIdWhenExistAndStatus200(){
        ServicoResponseDTO dto = new ServicoResponseDTO(1L, "Troca de óleo", new BigDecimal("150.00"), "ATIVO");

        when(service.buscarPorId(1L)).thenReturn(Optional.of(dto));

        ResponseEntity<ServicoResponseDTO> response = controller.buscarPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
        verify(service).buscarPorId(1L);
    }
    @Test
    @DisplayName("Deve retornar 404 ao buscar serviço inexistente")
    void shouldReturn404WhenServiceByIdNotFound(){
        when(service.buscarPorId(99L)).thenReturn(Optional.empty());

        ResponseEntity<ServicoResponseDTO> response = controller.buscarPorId(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(service).buscarPorId(99L);
    }
    @Test
    @DisplayName("Deve atualizar serviço existente e retornar status 200")
    void shouldUpdateServiceWhenExistsAndStatus200(){
        ServicoRequestDTO request = new ServicoRequestDTO("Alinhamento", new BigDecimal("200.00"), "ATIVO");
        ServicoResponseDTO dto = new ServicoResponseDTO(1L, "Alinhamento", new BigDecimal("200.00"), "ATIVO");

        when(service.atualizar(1L, request)).thenReturn(Optional.of(dto));

        ResponseEntity<ServicoResponseDTO> response = controller.atualizar(1L, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
        verify(service).atualizar(1L, request);
    }


}
