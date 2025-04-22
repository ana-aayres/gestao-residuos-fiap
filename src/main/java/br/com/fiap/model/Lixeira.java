package br.com.fiap.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_LIXEIRAS")
public class Lixeira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ajuste para MySQL compatível
    @Column(name = "ID_LIXEIRA")
    private Long idLixeira;

    private String endereco;
    private Double capacidade;
    private Boolean lotada;

    @ManyToOne
    @JoinColumn(name = "ID_AGENDAMENTO") // Corrigido para coincidir com o banco
    private Agendamento agendamento;

    public Long getIdLixeira() {
        return idLixeira;
    }

    public void setIdLixeira(Long idLixeira) {
        this.idLixeira = idLixeira;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Double capacidade) {
        this.capacidade = capacidade;
    }

    public Boolean getLotada() {
        return lotada;
    }

    public void setLotada(Boolean lotada) {
        this.lotada = lotada;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    @Override
    public String toString() {
        return "Lixeira{" +
                "idLixeira=" + idLixeira +
                ", endereco='" + endereco + '\'' +
                ", capacidade=" + capacidade +
                ", lotada=" + lotada +
                ", agendamento=" + (agendamento != null ? agendamento.getIdAgendamento() : "null") +
                '}';
    }
}