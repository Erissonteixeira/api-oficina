package io.github.com.Erissonteixeira.api_crudoficina.service;
import io.github.com.Erissonteixeira.api_crudoficina.dto.ServicoRequestDTO;
import io.github.com.Erissonteixeira.api_crudoficina.dto.ServicoResponseDTO;
import io.github.com.Erissonteixeira.api_crudoficina.model.Servico;
import io.github.com.Erissonteixeira.api_crudoficina.repository.ServicoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServicoServiceTest {
    @InjectMocks
    private ServicoService service;

    @Mock
    private ServicoRepository repository;

    @Test
    @DisplayName("Deve salvar um serviço com sucesso")
    void shouldSaveServiceSucessFully() {
        ServicoRequestDTO request = new ServicoRequestDTO("Troca de óleo", new BigDecimal("150.00"), "ATIVO");

        Servico entity = Servico.builder()
                .descricao("Troca de óleo")
                .valor(new BigDecimal("150.00"))
                .status("ATIVO")
                .build();

        when(repository.save(any(Servico.class))).thenReturn(entity);

        ServicoResponseDTO response = service.criar(request);

        assertEquals("Troca de óleo", response.getDescricao());
        assertEquals(new BigDecimal("150.00"), response.getValor());
        assertEquals("ATIVO", response.getStatus());

        verify(repository).save(any(Servico.class));
    }

    @Test
    @DisplayName("Deve listar todos os serviços")
    void shouldlistAllServices() {
        List<Servico> entidades = Arrays.asList(
                Servico.builder().descricao("Troca de óleo").valor(new BigDecimal("150.00")).status("ATIVO").build(),
                Servico.builder().descricao("Alinhamento").valor(new BigDecimal("200.00")).status("ATIVO").build()
        );

        when(repository.findAll()).thenReturn(entidades);

        List<ServicoResponseDTO> lista = service.listarTodos();

        assertEquals(2, lista.size());
        assertEquals("Troca de óleo", lista.get(0).getDescricao());
        assertEquals("Alinhamento", lista.get(1).getDescricao());

        verify(repository).findAll();
    }
    @Test
    @DisplayName("Deve buscar serviço por id existente")
    void shouldFindServiceByIdWhenExists(){

        Servico entity = Servico.builder()
                .descricao("Troca de óleo")
                .valor(new BigDecimal("150.00"))
                .status("ATIVO")
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        Optional<ServicoResponseDTO> response = service.buscarPorId(1L);

        assertEquals(true, response.isPresent());
        assertEquals("Troca de óleo", response.get().getDescricao());

        verify(repository).findById(1L);
    }
    @Test
    @DisplayName("Deve retornar vazio ao buscar serviço inexistente")
    void shouldReturnEmptyWhenServiceNotFound(){

        when(repository.findById(99L)).thenReturn(Optional.empty());

        Optional<ServicoResponseDTO> response = service.buscarPorId(99L);

        assertEquals(false, response.isPresent());

        verify(repository).findById(99L);
    }
    @Test
    @DisplayName("Deve atualizar um serviço existente com sucesso")
    void shouldUpdateServiceWhenExists(){
        ServicoRequestDTO request = new ServicoRequestDTO("Alinhamento", new BigDecimal("200.00"), "ATIVO");

        Servico existing = Servico.builder()
                .descricao("Troca de óleo")
                .valor(new BigDecimal("150.00"))
                .status("ATIVO")
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(existing));

        Servico updated = Servico.builder()
                .descricao(request.getDescricao())
                .valor(request.getValor())
                .status(request.getStatus())
                .build();

        when(repository.save(existing)).thenReturn(updated);

        Optional<ServicoResponseDTO> response = service.atualizar(1L, request);

        assertEquals(true, response.isPresent());
        assertEquals("Alinhamento", response.get().getDescricao());
        assertEquals(new BigDecimal("200.00"), response.get().getValor());
        assertEquals("ATIVO", response.get().getStatus());

        verify(repository).findById(1L);
        verify(repository).save(existing);

    }
    @Test
    @DisplayName("Deve retornar vazio ao tentar atualizar serviço inexistente")
    void shouldReturnEmptyWhenUpdateServicoNotFound(){
        ServicoRequestDTO request = new ServicoRequestDTO("Alinhamento", new BigDecimal("200.00"),"ATIVO");

        when(repository.findById(99L)).thenReturn(Optional.empty());

        Optional<ServicoResponseDTO> response = service.atualizar(99L, request);

        assertEquals(false, response.isPresent());

        verify(repository).findById(99L);
    }
    @Test
    @DisplayName("Deve excluir um serviço existente com sucesso")
    void shouldDeleteServiceWhenExists() {

        when(repository.existsById(1L)).thenReturn(true);

        boolean deteted = service.excluir(1L);

        assertEquals(true, deteted);

        verify(repository).existsById(1L);
        verify(repository).deleteById(1L);
    }
    @Test
    @DisplayName("Deve retornar false ao tentar excluir serviço inexistente")
    void shouldReturnFalseWhenDeleteServiceNotFound(){

        when(repository.existsById(99L)).thenReturn(false);

        boolean deleted = service.excluir(99L);

        assertEquals(false, deleted);

        verify(repository).existsById(99L);
    }
}