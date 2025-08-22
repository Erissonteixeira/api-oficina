package io.github.com.Erissonteixeira.api_crudoficina.model;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "servicos")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500, nullable = false)
    private String descricao;
    @Column(name = "Preco", precision = 10, scale = 2, nullable = false)
    private BigDecimal valor;
    @Column(nullable = false, length = 20)
    private String status;

    public Servico(){
    }

    public Servico(String descricao, BigDecimal valor, String status){
        this.descricao = descricao;
        this.valor = valor;
        this.status = status;
    }

    public Long getId() {
        return id;
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

