package br.com.fiap.controller;

import br.com.fiap.model.Lixeira;
import br.com.fiap.repository.LixeiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lixeiras")
public class LixeiraController {

    @Autowired
    private LixeiraRepository lixeiraRepository;

    // Método para listar todas as lixeiras
    @GetMapping
    public ResponseEntity<List<Lixeira>> getAllLixeiras() {
        List<Lixeira> lixeiras = lixeiraRepository.findAll();
        return ResponseEntity.ok(lixeiras);
    }

    // Método para criar uma nova lixeira
    @PostMapping
    public ResponseEntity<Lixeira> createLixeira(@RequestBody Lixeira lixeira) {
        try {
            Lixeira novaLixeira = lixeiraRepository.save(lixeira);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaLixeira);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{ID_LIXEIRA}")
    public ResponseEntity<String> deleteLixeira(@PathVariable("ID_LIXEIRA") Long idLixeira) {
        Optional<Lixeira> lixeira = lixeiraRepository.findById(idLixeira);

        if (lixeira.isPresent()) {
            try {
                lixeiraRepository.deleteById(idLixeira);
                return ResponseEntity.ok("Lixeira com ID " + idLixeira + " foi deletada com sucesso.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erro ao deletar a Lixeira com ID " + idLixeira + ": " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lixeira não encontrada.");
        }
    }
}