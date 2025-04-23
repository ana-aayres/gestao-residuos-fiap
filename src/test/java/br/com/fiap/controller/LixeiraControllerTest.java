package br.com.fiap.controller;

import br.com.fiap.model.Lixeira;
import br.com.fiap.repository.LixeiraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class LixeiraControllerTest {

    @Mock
    private LixeiraRepository lixeiraRepository;

    @InjectMocks
    private LixeiraController lixeiraController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllLixeiras() {
        Lixeira lixeira1 = new Lixeira();
        lixeira1.setIdLixeira(1L);
        lixeira1.setEndereco("Rua A");
        lixeira1.setCapacidade(50.0);
        lixeira1.setLotada(false);

        Lixeira lixeira2 = new Lixeira();
        lixeira2.setIdLixeira(2L);
        lixeira2.setEndereco("Rua B");
        lixeira2.setCapacidade(30.0);
        lixeira2.setLotada(true);

        List<Lixeira> lixeiras = Arrays.asList(lixeira1, lixeira2);
        when(lixeiraRepository.findAll()).thenReturn(lixeiras);

        List<Lixeira> result = lixeiraController.getAllLixeiras().getBody();
        assertEquals(2, result.size());
        assertEquals("Rua A", result.get(0).getEndereco());
    }

    @Test
    void testCreateLixeira() {
        Lixeira novaLixeira = new Lixeira();
        novaLixeira.setIdLixeira(3L);
        novaLixeira.setEndereco("Rua C");
        novaLixeira.setCapacidade(40.0);
        novaLixeira.setLotada(false);

        when(lixeiraRepository.save(novaLixeira)).thenReturn(novaLixeira);

        Lixeira result = lixeiraController.createLixeira(novaLixeira).getBody();
        assertNotNull(result);
        assertEquals("Rua C", result.getEndereco());
    }

    @Test
    void testDeleteLixeiraSuccess() {
        Long idLixeira = 1L;
        when(lixeiraRepository.existsById(idLixeira)).thenReturn(true);

        ResponseEntity<?> response = lixeiraController.deleteLixeira(idLixeira);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testDeleteLixeiraNotFound() {
        Long idLixeira = 99L;
        when(lixeiraRepository.existsById(idLixeira)).thenReturn(false);

        ResponseEntity<?> response = lixeiraController.deleteLixeira(idLixeira);
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Lixeira não encontrada.", response.getBody());
    }
}