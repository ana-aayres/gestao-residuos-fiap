package br.com.fiap.controller;

import br.com.fiap.model.Agendamento;
import br.com.fiap.repository.AgendamentoRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @GetMapping("/coleta-de-lixo")
    public List<Agendamento> getAllAgendamentos() {
        return agendamentoRepository.findAll();
    }

    @PostMapping("/agendamento-de-coleta")
    public ResponseEntity<Agendamento> createAgendamento(@RequestBody Agendamento agendamento) {
        try {
            agendamento.getLixeiras().forEach(lixeira -> lixeira.setAgendamento(agendamento));
            Agendamento createdAgendamento = agendamentoRepository.save(agendamento);
            return ResponseEntity.ok(createdAgendamento);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Erro ao criar agendamento: " + e.getMessage());
        }
    }

    @PutMapping("/agendamento-de-coleta/{ID_AGENDAMENTO}")
    public ResponseEntity<Agendamento> updateAgendamento(@PathVariable("ID_AGENDAMENTO") Long idAgendamento, @RequestBody Agendamento agendamentoDetails) {
        Agendamento agendamento = agendamentoRepository.findById(idAgendamento)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com id: " + idAgendamento));

        agendamento.setVeiculo(agendamentoDetails.getVeiculo());
        agendamento.setRota(agendamentoDetails.getRota());
        agendamento.setData(agendamentoDetails.getData());
        agendamento.setLixeiras(agendamentoDetails.getLixeiras());

        final Agendamento updatedAgendamento = agendamentoRepository.save(agendamento);
        return ResponseEntity.ok(updatedAgendamento);
    }

    @DeleteMapping("/agendamento-de-coleta/{ID_AGENDAMENTO}")
    public ResponseEntity<?> deleteAgendamento(@PathVariable("ID_AGENDAMENTO") Long idAgendamento) {
        try {
            if (agendamentoRepository.existsById(idAgendamento)) {
                agendamentoRepository.deleteById(idAgendamento);
                return ResponseEntity.ok("Agendamento com ID " + idAgendamento + " foi deletado com sucesso.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao deletar o Agendamento com ID " + idAgendamento + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar o Agendamento.");
        }
    }


}
