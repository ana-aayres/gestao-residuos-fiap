package br.com.fiap.controller;

import br.com.fiap.model.Lixeira;
import br.com.fiap.repository.LixeiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/lixeiras")
public class LixeiraController {

    @Autowired
    private LixeiraRepository lixeiraRepository;

    @GetMapping
    public List<Lixeira> getAllLixeiras() {
        return lixeiraRepository.findAll();
    }

    @PostMapping
    public Lixeira createLixeira(@RequestBody Lixeira lixeira) {
        return lixeiraRepository.save(lixeira);
    }

    @DeleteMapping("/{ID_LIXEIRA}")
    public ResponseEntity<?> deleteLixeira(@PathVariable("ID_LIXEIRA") Long idLixeira) {
        try {
            if (lixeiraRepository.existsById(idLixeira)) {
                lixeiraRepository.deleteById(idLixeira);
                return ResponseEntity.ok("Lixeira com ID " + idLixeira + " foi deletada com sucesso.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lixeira não encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao deletar a Lixeira com ID " + idLixeira + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar a Lixeira.");
        }
    }



}
