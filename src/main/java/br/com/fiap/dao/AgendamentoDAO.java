package br.com.fiap.dao;

import br.com.fiap.model.Agendamento;
import jakarta.persistence.EntityManager;

public class AgendamentoDAO {

    private EntityManager em; //fazer a presistência de um objeto

    public AgendamentoDAO(EntityManager em){ //construtor
        this.em = em;
    }
    public void salvar(Agendamento agendamento){
        em.persist(agendamento);
    }
    public void atualizar(Agendamento agendamento){
        em.merge(agendamento);
    }
    public void excluir(Agendamento agendamento){
        Agendamento agendamentoExcluir = em.find(Agendamento.class, agendamento.getIdAgendamento());
        em.remove(agendamentoExcluir);
    }

    public void remover(Agendamento agendamento){
        Agendamento agendamentoExcluir = em.find(Agendamento.class, agendamento.getIdAgendamento());
        em.remove(agendamentoExcluir);
    }

}
