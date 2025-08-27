package io.github.com.Erissonteixeira.api_crudoficina.service;

import io.github.com.Erissonteixeira.api_crudoficina.dto.ServicoRequestDTO;
import io.github.com.Erissonteixeira.api_crudoficina.dto.ServicoResponseDTO;
import io.github.com.Erissonteixeira.api_crudoficina.model.Servico;
import io.github.com.Erissonteixeira.api_crudoficina.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoService {
    private final ServicoRepository repository;

    public ServicoService(ServicoRepository repository) {
        this.repository = repository;
    }
    public ServicoResponseDTO criar(ServicoRequestDTO dto){
        Servico servico = new Servico(dto.getDescricao(), dto.getValor(), dto.getStatus());
        Servico salvo = repository.save(servico);
        return new ServicoResponseDTO(salvo.getId(), salvo.getDescricao(), salvo.getValor(), salvo.getStatus());
    }
    public List<ServicoResponseDTO> listarTodos(){
        return repository.findAll()
                .stream()
                .map(s -> new ServicoResponseDTO(s.getId(), s.getDescricao(), s.getValor(), s.getStatus()))
                .collect(Collectors.toList());
    }

}
