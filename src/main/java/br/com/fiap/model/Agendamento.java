package br.com.fiap.model;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_AGENDAMENTOS")
public class Agendamento {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_AGENDAMENTOS")
    @SequenceGenerator(
            name = "SEQ_AGENDAMENTOS",
            sequenceName = "SEQ_AGENDAMENTOS",
            allocationSize = 1)
    //a seguence tem que ser criada diretamente no Oracle, exatamente com o mesmo nome que está aqui
    @Column(name = "ID_AGENDAMENTO")
    private Long idAgendamento;
    private String veiculo;
    private String rota;
    private LocalDate data;
    @OneToMany(mappedBy = "agendamento", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Lixeira> lixeiras;

    public Long getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Long idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getRota() {
        return rota;
    }

    public void setRota(String rota) {
        this.rota = rota;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<Lixeira> getLixeiras() {
        return lixeiras;
    }

    public void setLixeiras(List<Lixeira> lixeiras) {
        this.lixeiras = lixeiras;
    }

    @Override
    public String toString() {
        return "Agendamento{" +
                "idAgendamento=" + idAgendamento +
                ", veiculo='" + veiculo + '\'' +
                ", rota='" + rota + '\'' +
                ", data=" + data + '\'' +
                ", lixeiras=" + lixeiras +
                '}';
    }
}
