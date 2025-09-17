package io.github.com.Erissonteixeira.api_crudoficina.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ServicoRequestDTO {
    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;
    @NotNull(message = "Valor é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Valor deve ser maior que zero")
    @Digits(integer = 8, fraction = 2, message = "Valor deve ter no máximo 8 dígitos e 2 decimais")
    private BigDecimal valor;
    @NotBlank(message = "Status é obrigatório")
    private String status;

    public ServicoRequestDTO(){
    }

    public ServicoRequestDTO(String descricao, BigDecimal valor, String status){
        this.descricao = descricao;
        this.valor = valor;
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
