package io.github.com.Erissonteixeira.api_crudoficina.service;

import io.github.com.Erissonteixeira.api_crudoficina.dto.ServicoRequestDTO;
import io.github.com.Erissonteixeira.api_crudoficina.dto.ServicoResponseDTO;
import io.github.com.Erissonteixeira.api_crudoficina.model.Servico;
import io.github.com.Erissonteixeira.api_crudoficina.repository.ServicoRepository;
import org.springframework.stereotype.Service;

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


}
