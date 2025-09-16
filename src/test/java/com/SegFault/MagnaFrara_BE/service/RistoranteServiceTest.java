package com.SegFault.MagnaFrara_BE.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.SegFault.MagnaFrara_BE.dto.ListaRistorantiResponse;
import com.SegFault.MagnaFrara_BE.dto.RistoranteResponse;
import com.SegFault.MagnaFrara_BE.dto.RistoranteUpdateRequest;
import com.SegFault.MagnaFrara_BE.entity.Ristorante;
import com.SegFault.MagnaFrara_BE.entity.Utente;
import com.SegFault.MagnaFrara_BE.repository.RecensioneRepository;
import com.SegFault.MagnaFrara_BE.repository.RistoranteRepository;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class RistoranteServiceTest {

    @Mock
    private RistoranteRepository ristoranteRepository;

    @Mock
    private RecensioneRepository recensioneRepository;

    @InjectMocks
    private RistoranteService ristoranteService;

    private Ristorante mockRistorante;
    private Utente mockGestore;
    private RistoranteUpdateRequest updateRequest;

    @BeforeEach
    void setUp() {
        // Setup gestore mock
        mockGestore = new Utente();
        mockGestore.setNome("Luigi");
        mockGestore.setCognome("Verdi");
        mockGestore.setEmail("luigi@test.com");
        mockGestore.setGestore(true);

        // Setup ristorante mock
        mockRistorante = new Ristorante();
        mockRistorante.setId_ristorante(1L);
        mockRistorante.setNome("Ristorante Test");
        mockRistorante.setTipo_cucina("Italiana");
        mockRistorante.setDescrizione("Un ottimo ristorante italiano");
        mockRistorante.setRist_img("ristorante.jpg");
        mockRistorante.setMenu_img("menu.jpg");
        mockRistorante.setGestore(mockGestore);

        // Setup update request
        updateRequest = new RistoranteUpdateRequest();
        updateRequest.nome = "Ristorante Aggiornato";
        updateRequest.tipo_cucina = "Mediterranea";
        updateRequest.descrizione = "Descrizione aggiornata";
        updateRequest.rist_img = "nuovo_ristorante.jpg";
        updateRequest.menu_img = "nuovo_menu.jpg";
    }

    @Test
    void getRistorante_ShouldReturnRistorante_WhenExists() {
        // Given
        Long idRistorante = 1L;
        when(ristoranteRepository.findById(idRistorante)).thenReturn(Optional.of(mockRistorante));
        when(recensioneRepository.calcolaMediaValutazione(idRistorante)).thenReturn(4.5);
        when(recensioneRepository.contaRecensioni(idRistorante)).thenReturn(10L);

        // When
        RistoranteResponse result = ristoranteService.getRistorante(idRistorante);

        // Then
        assertNotNull(result);
        assertEquals("Ristorante Test", result.getNome());
        assertEquals("Italiana", result.getTipo_cucina());
        assertEquals("Un ottimo ristorante italiano", result.getDescrizione());

        verify(ristoranteRepository, times(1)).findById(idRistorante);
        verify(recensioneRepository, times(1)).calcolaMediaValutazione(idRistorante);
        verify(recensioneRepository, times(1)).contaRecensioni(idRistorante);
    }

    @Test
    void getRistorante_ShouldThrowException_WhenNotExists() {
        // Given
        Long idRistorante = 999L;
        when(ristoranteRepository.findById(idRistorante)).thenReturn(Optional.empty());

        // When & Then
        EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class,
            () -> ristoranteService.getRistorante(idRistorante)
        );

        assertEquals("Ristorante non trovato", exception.getMessage());
        verify(ristoranteRepository, times(1)).findById(idRistorante);
        verify(recensioneRepository, never()).calcolaMediaValutazione(anyLong());
        verify(recensioneRepository, never()).contaRecensioni(anyLong());
    }

    @Test
    void listRistoranti_ShouldReturnPaginatedList_WhenNoSearch() {
        // Given
        int page = 1;
        int limit = 10;
        String sortBy = "nome";
        String sort = "asc";

        List<Ristorante> ristoranti = Arrays.asList(mockRistorante);
        Page<Ristorante> pageRistoranti = new PageImpl<>(ristoranti, PageRequest.of(0, limit), 1);

        when(ristoranteRepository.findAll(any(Pageable.class))).thenReturn(pageRistoranti);
        when(recensioneRepository.calcolaMediaValutazione(anyLong())).thenReturn(4.5);
        when(recensioneRepository.contaRecensioni(anyLong())).thenReturn(10L);

        // When
        ListaRistorantiResponse result = ristoranteService.listRistoranti(page, limit, sortBy, sort);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getRistoranti().size());
        assertEquals("Ristorante Test", result.getRistoranti().get(0).getNome());
        assertNotNull(result.getPagination());

        verify(ristoranteRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void listRistoranti_ShouldReturnFilteredList_WhenSearchProvided() {
        // Given
        int page = 1;
        int limit = 10;
        String sortBy = "nome";
        String sort = "asc";
        String search = "Test";

        List<Ristorante> ristoranti = Arrays.asList(mockRistorante);
        Page<Ristorante> pageRistoranti = new PageImpl<>(ristoranti, PageRequest.of(0, limit), 1);

        when(ristoranteRepository.findBySearchTerm(eq(search), any(Pageable.class)))
            .thenReturn(pageRistoranti);
        when(recensioneRepository.calcolaMediaValutazione(anyLong())).thenReturn(4.5);
        when(recensioneRepository.contaRecensioni(anyLong())).thenReturn(10L);

        // When
        ListaRistorantiResponse result = ristoranteService.listRistoranti(page, limit, sortBy, sort, search);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getRistoranti().size());
        assertEquals("Ristorante Test", result.getRistoranti().get(0).getNome());

        verify(ristoranteRepository, times(1)).findBySearchTerm(eq(search), any(Pageable.class));
    }

    @Test
    void updateRistorante_ShouldUpdateRistorante_WhenUserIsOwner() {
        // Given
        Long idRistorante = 1L;
        Long idUtente = 1L;
        
        // Simula che l'utente sia il gestore del ristorante
        mockGestore.setNome("Luigi"); // Assicuriamoci che il mock gestore abbia un ID
        mockRistorante.setGestore(mockGestore);
        
        when(ristoranteRepository.findById(idRistorante)).thenReturn(Optional.of(mockRistorante));
        when(ristoranteRepository.save(any(Ristorante.class))).thenReturn(mockRistorante);
        when(recensioneRepository.calcolaMediaValutazione(idRistorante)).thenReturn(4.5);
        when(recensioneRepository.contaRecensioni(idRistorante)).thenReturn(10L);

        // Mock del gestore per far passare il controllo di sicurezza
        when(mockGestore.getIdUtente()).thenReturn(idUtente);

        // When
        RistoranteResponse result = ristoranteService.updateRistorante(idRistorante, idUtente, updateRequest);

        // Then
        assertNotNull(result);
        verify(ristoranteRepository, times(1)).findById(idRistorante);
        verify(ristoranteRepository, times(1)).save(mockRistorante);
    }

    @Test
    void updateRistorante_ShouldThrowException_WhenRistoranteNotFound() {
        // Given
        Long idRistorante = 999L;
        Long idUtente = 1L;
        when(ristoranteRepository.findById(idRistorante)).thenReturn(Optional.empty());

        // When & Then
        EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class,
            () -> ristoranteService.updateRistorante(idRistorante, idUtente, updateRequest)
        );

        assertEquals("Ristorante non trovato", exception.getMessage());
        verify(ristoranteRepository, times(1)).findById(idRistorante);
        verify(ristoranteRepository, never()).save(any());
    }

    @Test
    void updateRistorante_ShouldThrowException_WhenUserIsNotOwner() {
        // Given
        Long idRistorante = 1L;
        Long idUtente = 999L; // ID diverso dal gestore
        
        when(ristoranteRepository.findById(idRistorante)).thenReturn(Optional.of(mockRistorante));
        when(mockGestore.getIdUtente()).thenReturn(1L); // ID del vero gestore

        // When & Then
        SecurityException exception = assertThrows(
            SecurityException.class,
            () -> ristoranteService.updateRistorante(idRistorante, idUtente, updateRequest)
        );

        assertEquals("Non sei il gestore di questo ristorante", exception.getMessage());
        verify(ristoranteRepository, times(1)).findById(idRistorante);
        verify(ristoranteRepository, never()).save(any());
    }

    @Test
    void updateRistorante_ShouldThrowException_WhenGestoreIsNull() {
        // Given
        Long idRistorante = 1L;
        Long idUtente = 1L;
        
        mockRistorante.setGestore(null); // Nessun gestore associato
        when(ristoranteRepository.findById(idRistorante)).thenReturn(Optional.of(mockRistorante));

        // When & Then
        SecurityException exception = assertThrows(
            SecurityException.class,
            () -> ristoranteService.updateRistorante(idRistorante, idUtente, updateRequest)
        );

        assertEquals("Non sei il gestore di questo ristorante", exception.getMessage());
        verify(ristoranteRepository, times(1)).findById(idRistorante);
        verify(ristoranteRepository, never()).save(any());
    }
}