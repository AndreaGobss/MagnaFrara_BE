package com.SegFault.MagnaFrara_BE.service;

import java.time.LocalDateTime;
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

import com.SegFault.MagnaFrara_BE.dto.ListaRecensioniResponse;
import com.SegFault.MagnaFrara_BE.dto.RecensioneRequest;
import com.SegFault.MagnaFrara_BE.dto.RecensioneResponse;
import com.SegFault.MagnaFrara_BE.entity.Recensione;
import com.SegFault.MagnaFrara_BE.entity.Ristorante;
import com.SegFault.MagnaFrara_BE.entity.Utente;
import com.SegFault.MagnaFrara_BE.repository.RecensioneRepository;
import com.SegFault.MagnaFrara_BE.repository.RistoranteRepository;
import com.SegFault.MagnaFrara_BE.repository.UtenteRepository;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class RecensioneServiceTest {

    @Mock
    private RecensioneRepository recensioneRepository;

    @Mock
    private RistoranteRepository ristoranteRepository;

    @Mock
    private UtenteRepository utenteRepository;

    @InjectMocks
    private RecensioneService recensioneService;

    private RecensioneRequest recensioneRequest;
    private Utente mockUtente;
    private Ristorante mockRistorante;
    private Recensione mockRecensione;

    @BeforeEach
    void setUp() {
        // Setup utente mock
        mockUtente = new Utente();
        mockUtente.setNome("Mario");
        mockUtente.setCognome("Rossi");
        mockUtente.setEmail("mario@test.com");
        mockUtente.setGestore(false);

        // Setup ristorante mock
        mockRistorante = new Ristorante();
        mockRistorante.setId_ristorante(1L);
        mockRistorante.setNome("Ristorante Test");
        mockRistorante.setTipo_cucina("Italiana");
        mockRistorante.setDescrizione("Un ottimo ristorante");
        mockRistorante.setGestore(mockUtente);

        // Setup recensione request
        recensioneRequest = new RecensioneRequest();
        recensioneRequest.id_utente = 1L;
        recensioneRequest.titolo = "Ottima esperienza";
        recensioneRequest.testo = "Il cibo era delizioso e il servizio eccellente";
        recensioneRequest.valutazione = 5;

        // Setup recensione mock
        mockRecensione = new Recensione();
        mockRecensione.setId_rec(1L);
        mockRecensione.setUtente(mockUtente);
        mockRecensione.setRistorante(mockRistorante);
        mockRecensione.setTitolo("Ottima esperienza");
        mockRecensione.setTesto("Il cibo era delizioso e il servizio eccellente");
        mockRecensione.setValutazione(5);
        mockRecensione.setData_pubb(LocalDateTime.now());
    }

    @Test
    void creaRecensione_ShouldCreateRecensione_WhenValidRequest() {
        // Given
        Long idRistorante = 1L;
        when(ristoranteRepository.findById(idRistorante)).thenReturn(Optional.of(mockRistorante));
        when(utenteRepository.findById(recensioneRequest.id_utente)).thenReturn(Optional.of(mockUtente));
        when(recensioneRepository.save(any(Recensione.class))).thenReturn(mockRecensione);

        // When
        RecensioneResponse result = recensioneService.creaRecensione(idRistorante, recensioneRequest);

        // Then
        assertNotNull(result);
        assertEquals("Ottima esperienza", result.titolo);
        assertEquals("Il cibo era delizioso e il servizio eccellente", result.testo);
        assertEquals(5, result.valutazione);

        verify(ristoranteRepository, times(1)).findById(idRistorante);
        verify(utenteRepository, times(1)).findById(recensioneRequest.id_utente);
        verify(recensioneRepository, times(1)).save(any(Recensione.class));
    }

    @Test
    void creaRecensione_ShouldThrowException_WhenRistoranteNotFound() {
        // Given
        Long idRistorante = 999L;
        when(ristoranteRepository.findById(idRistorante)).thenReturn(Optional.empty());

        // When & Then
        EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class,
            () -> recensioneService.creaRecensione(idRistorante, recensioneRequest)
        );

        assertEquals("Ristorante non trovato", exception.getMessage());
        verify(ristoranteRepository, times(1)).findById(idRistorante);
        verify(utenteRepository, never()).findById(anyLong());
        verify(recensioneRepository, never()).save(any());
    }

    @Test
    void creaRecensione_ShouldThrowException_WhenUtenteNotFound() {
        // Given
        Long idRistorante = 1L;
        when(ristoranteRepository.findById(idRistorante)).thenReturn(Optional.of(mockRistorante));
        when(utenteRepository.findById(recensioneRequest.id_utente)).thenReturn(Optional.empty());

        // When & Then
        EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class,
            () -> recensioneService.creaRecensione(idRistorante, recensioneRequest)
        );

        assertEquals("Utente non trovato", exception.getMessage());
        verify(ristoranteRepository, times(1)).findById(idRistorante);
        verify(utenteRepository, times(1)).findById(recensioneRequest.id_utente);
        verify(recensioneRepository, never()).save(any());
    }

    @Test
    void getRecensioniRistorante_ShouldReturnRecensioni_WhenRistoranteExists() {
        // Given
        Long idRistorante = 1L;
        int page = 1;
        int limit = 10;
        String sortBy = "data_pubb";
        String sort = "desc";

        List<Recensione> recensioni = Arrays.asList(mockRecensione);
        Page<Recensione> pageRecensioni = new PageImpl<>(recensioni, PageRequest.of(0, limit), 1);

        when(ristoranteRepository.findById(idRistorante)).thenReturn(Optional.of(mockRistorante));
        when(recensioneRepository.findByRistoranteId(eq(idRistorante), any(Pageable.class)))
            .thenReturn(pageRecensioni);
        when(recensioneRepository.calcolaMediaValutazione(idRistorante)).thenReturn(4.5);

        // When
        ListaRecensioniResponse result = recensioneService.getRecensioniRistorante(
            idRistorante, page, limit, sortBy, sort);

        // Then
        assertNotNull(result);
        assertEquals(1, result.recensioni.size());
        assertEquals("Ottima esperienza", result.recensioni.get(0).titolo);
        assertNotNull(result.pagination);
        assertNotNull(result.ristorante);
        assertEquals("Ristorante Test", result.ristorante.nome);

        verify(ristoranteRepository, times(1)).findById(idRistorante);
        verify(recensioneRepository, times(1)).findByRistoranteId(eq(idRistorante), any(Pageable.class));
    }

    @Test
    void getRecensioniRistorante_ShouldThrowException_WhenRistoranteNotFound() {
        // Given
        Long idRistorante = 999L;
        when(ristoranteRepository.findById(idRistorante)).thenReturn(Optional.empty());

        // When & Then
        EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class,
            () -> recensioneService.getRecensioniRistorante(idRistorante, 1, 10, "data_pubb", "desc")
        );

        assertEquals("Ristorante non trovato", exception.getMessage());
        verify(ristoranteRepository, times(1)).findById(idRistorante);
        verify(recensioneRepository, never()).findByRistoranteId(anyLong(), any());
    }

    @Test
    void getRecensioniUtente_ShouldReturnRecensioni_WhenUtenteExists() {
        // Given
        Long idUtente = 1L;
        int page = 1;
        int limit = 10;
        String sortBy = "data_pubb";
        String sort = "desc";

        List<Recensione> recensioni = Arrays.asList(mockRecensione);
        Page<Recensione> pageRecensioni = new PageImpl<>(recensioni, PageRequest.of(0, limit), 1);

        when(utenteRepository.findById(idUtente)).thenReturn(Optional.of(mockUtente));
        when(recensioneRepository.findByUtenteId(eq(idUtente), any(Pageable.class)))
            .thenReturn(pageRecensioni);
        when(recensioneRepository.calcolaMediaValutazioniUtente(idUtente)).thenReturn(4.2);

        // When
        ListaRecensioniResponse result = recensioneService.getRecensioniUtente(
            idUtente, page, limit, sortBy, sort);

        // Then
        assertNotNull(result);
        assertEquals(1, result.recensioni.size());
        assertEquals("Ottima esperienza", result.recensioni.get(0).titolo);
        assertNotNull(result.pagination);
        assertNotNull(result.utente);
        assertEquals("Mario", result.utente.nome);
        assertEquals("Rossi", result.utente.cognome);

        verify(utenteRepository, times(1)).findById(idUtente);
        verify(recensioneRepository, times(1)).findByUtenteId(eq(idUtente), any(Pageable.class));
    }

    @Test
    void getAllRecensioni_ShouldReturnAllRecensioni() {
        // Given
        int page = 1;
        int limit = 10;
        String sortBy = "data_pubb";
        String sort = "desc";

        List<Recensione> recensioni = Arrays.asList(mockRecensione);
        Page<Recensione> pageRecensioni = new PageImpl<>(recensioni, PageRequest.of(0, limit), 1);

        when(recensioneRepository.findAll(any(Pageable.class))).thenReturn(pageRecensioni);

        // When
        ListaRecensioniResponse result = recensioneService.getAllRecensioni(page, limit, sortBy, sort);

        // Then
        assertNotNull(result);
        assertEquals(1, result.recensioni.size());
        assertEquals("Ottima esperienza", result.recensioni.get(0).titolo);
        assertNotNull(result.pagination);

        verify(recensioneRepository, times(1)).findAll(any(Pageable.class));
    }
}