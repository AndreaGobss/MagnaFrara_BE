package com.SegFault.MagnaFrara_BE.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.SegFault.MagnaFrara_BE.dto.ListaRecensioniResponse;
import com.SegFault.MagnaFrara_BE.dto.PaginationDTO;
import com.SegFault.MagnaFrara_BE.dto.RecensioneRequest;
import com.SegFault.MagnaFrara_BE.dto.RecensioneResponse;
import com.SegFault.MagnaFrara_BE.entity.Recensione;
import com.SegFault.MagnaFrara_BE.entity.Ristorante;
import com.SegFault.MagnaFrara_BE.entity.Utente;
import com.SegFault.MagnaFrara_BE.repository.RecensioneRepository;
import com.SegFault.MagnaFrara_BE.repository.RistoranteRepository;
import com.SegFault.MagnaFrara_BE.repository.UtenteRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RecensioneService {

    private final RecensioneRepository recensioneRepo;
    private final RistoranteRepository ristoranteRepo;
    private final UtenteRepository utenteRepo;

    public RecensioneService(RecensioneRepository recensioneRepo, 
                           RistoranteRepository ristoranteRepo,
                           UtenteRepository utenteRepo) {
        this.recensioneRepo = recensioneRepo;
        this.ristoranteRepo = ristoranteRepo;
        this.utenteRepo = utenteRepo;
    }

    // -------- CREA NUOVA RECENSIONE
    public RecensioneResponse creaRecensione(Long idRistorante, RecensioneRequest request) {
        // Verifica che ristorante esista
        Ristorante ristorante = ristoranteRepo.findById(idRistorante)
                .orElseThrow(() -> new EntityNotFoundException("Ristorante non trovato"));
        
        // Verifica che utente esista
        Utente utente = utenteRepo.findById(request.id_utente)
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        // Crea recensione
        Recensione recensione = new Recensione();
        recensione.setUtente(utente);
        recensione.setRistorante(ristorante);
        recensione.setTitolo(request.titolo);
        recensione.setTesto(request.testo);
        recensione.setValutazione(request.valutazione);
        recensione.setData_pubb(LocalDateTime.now());

        Recensione saved = recensioneRepo.save(recensione);
        return new RecensioneResponse(saved);
    }

    // -------- RECENSIONI DI UN RISTORANTE
    public ListaRecensioniResponse getRecensioniRistorante(Long idRistorante, int page, int limit, 
                                                           String sortBy, String sort) {
        // Verifica che ristorante esista
        Ristorante ristorante = ristoranteRepo.findById(idRistorante)
                .orElseThrow(() -> new EntityNotFoundException("Ristorante non trovato"));

        // Configurazione paginazione e ordinamento
        Sort.Direction direction = sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(direction, sortBy));
        
        // Recupera recensioni
        Page<Recensione> pageRecensioni = recensioneRepo.findByRistoranteId(idRistorante, pageable);
        
        // Mappa in DTO
        List<RecensioneResponse> list = pageRecensioni.getContent().stream()
                .map(RecensioneResponse::new)
                .toList();

        // Paginazione
        PaginationDTO pagination = new PaginationDTO(page, limit, pageRecensioni.getTotalElements());
        
        // Info ristorante
        ListaRecensioniResponse.RistoranteInfoDTO ristoranteInfo = 
                new ListaRecensioniResponse.RistoranteInfoDTO(ristorante.getId_ristorante(), ristorante.getNome());
        
        // Statistiche
        Double avgValutazione = recensioneRepo.calcolaMediaValutazione(idRistorante);
        Map<String, Long> distribuzione = getDistribuzioneVoti(idRistorante);
        ListaRecensioniResponse.StatsDTO stats = 
                new ListaRecensioniResponse.StatsDTO(avgValutazione, distribuzione);

        return new ListaRecensioniResponse(list, pagination, ristoranteInfo, stats);
    }

    // -------- RECENSIONI DI UN UTENTE
    public ListaRecensioniResponse getRecensioniUtente(Long idUtente, int page, int limit, 
                                                       String sortBy, String sort) {
        // Verifica che utente esista
        Utente utente = utenteRepo.findById(idUtente)
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        // Configurazione paginazione e ordinamento
        Sort.Direction direction = sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(direction, sortBy));
        
        // Recupera recensioni
        Page<Recensione> pageRecensioni = recensioneRepo.findByUtenteId(idUtente, pageable);
        
        // Mappa in DTO
        List<RecensioneResponse> list = pageRecensioni.getContent().stream()
                .map(RecensioneResponse::new)
                .toList();

        // Paginazione
        PaginationDTO pagination = new PaginationDTO(page, limit, pageRecensioni.getTotalElements());
        
        // Info utente
        ListaRecensioniResponse.UtenteInfoDTO utenteInfo = 
                new ListaRecensioniResponse.UtenteInfoDTO(utente.getIdUtente(), utente.getNome(), utente.getCognome());

        // Statistiche utente
        Double avgValutazione = recensioneRepo.calcolaMediaValutazioniUtente(idUtente);
        if (avgValutazione == null) avgValutazione = 0.0;
        
        Map<String, Long> distribuzione = getDistribuzioneVotiUtente(idUtente);
        ListaRecensioniResponse.StatsDTO stats = 
                new ListaRecensioniResponse.StatsDTO(avgValutazione, distribuzione);

        return new ListaRecensioniResponse(list, pagination, utenteInfo, stats);
    }

    // -------- TUTTE LE RECENSIONI (generale)
    public ListaRecensioniResponse getAllRecensioni(int page, int limit, String sortBy, String sort) {
        // Configurazione paginazione e ordinamento
        Sort.Direction direction = sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(direction, sortBy));
        
        // Recupera recensioni
        Page<Recensione> pageRecensioni = recensioneRepo.findAll(pageable);
        
        // Mappa in DTO
        List<RecensioneResponse> list = pageRecensioni.getContent().stream()
                .map(RecensioneResponse::new)
                .toList();

        // Paginazione
        PaginationDTO pagination = new PaginationDTO(page, limit, pageRecensioni.getTotalElements());

        return new ListaRecensioniResponse(list, pagination);
    }

    // -------- METODO PRIVATO: Distribuzione voti
    private Map<String, Long> getDistribuzioneVoti(Long idRistorante) {
        List<Object[]> results = recensioneRepo.getDistribuzioneVoti(idRistorante);
        Map<String, Long> distribuzione = new HashMap<>();
        
        // Inizializza tutti i voti a 0
        for (int i = 1; i <= 5; i++) {
            distribuzione.put(String.valueOf(i), 0L);
        }
        
        // Riempi con i risultati reali
        for (Object[] result : results) {
            Integer voto = (Integer) result[0];
            Long count = (Long) result[1];
            distribuzione.put(voto.toString(), count);
        }
        
        return distribuzione;
    }

    // -------- METODO PRIVATO: Distribuzione voti utente
    private Map<String, Long> getDistribuzioneVotiUtente(Long idUtente) {
        List<Object[]> results = recensioneRepo.getDistribuzioneVotiUtente(idUtente);
        Map<String, Long> distribuzione = new HashMap<>();
        
        // Inizializza tutti i voti a 0
        for (int i = 1; i <= 5; i++) {
            distribuzione.put(String.valueOf(i), 0L);
        }
        
        // Riempi con i risultati reali
        for (Object[] result : results) {
            Integer voto = (Integer) result[0];
            Long count = (Long) result[1];
            distribuzione.put(voto.toString(), count);
        }
        
        return distribuzione;
    }
}
