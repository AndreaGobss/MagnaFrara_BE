package com.SegFault.MagnaFrara_BE.service;

import com.SegFault.MagnaFrara_BE.dto.ListaRistorantiResponse;
import com.SegFault.MagnaFrara_BE.dto.RistoranteResponse;
import com.SegFault.MagnaFrara_BE.dto.RistoranteUpdateRequest;
import com.SegFault.MagnaFrara_BE.entity.Ristorante;
import com.SegFault.MagnaFrara_BE.entity.Utente;
import com.SegFault.MagnaFrara_BE.repository.RistoranteRepository;
import com.SegFault.MagnaFrara_BE.repository.RecensioneRepository;
import com.SegFault.MagnaFrara_BE.dto.RistoranteListItemDTO;
import com.SegFault.MagnaFrara_BE.dto.PaginationDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class RistoranteService {

    private final RistoranteRepository ristoranteRepo;
    private final RecensioneRepository recensioneRepo;

    public RistoranteService(RistoranteRepository ristoranteRepo, RecensioneRepository recensioneRepo) {
        this.ristoranteRepo = ristoranteRepo;
        this.recensioneRepo = recensioneRepo;
    }

    // -------- GET singolo ristorante
    public RistoranteResponse getRistorante(Long id) {
        Ristorante r = ristoranteRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ristorante non trovato"));

        Double media = recensioneRepo.calcolaMediaValutazione(r.getId_ristorante());
        Long count = recensioneRepo.contaRecensioni(r.getId_ristorante());

        return new RistoranteResponse(r, media, count);
    }

    // -------- LISTA ristoranti con pagination, ordinamento e ricerca
    public ListaRistorantiResponse listRistoranti(int page, int limit, String sortBy, String sort, String search) {
        return listRistoranti(page, limit, sortBy, sort, search, null);
    }

    // Overload per compatibilità con chiamate esistenti (senza parametro search)
    public ListaRistorantiResponse listRistoranti(int page, int limit, String sortBy, String sort) {
        return listRistoranti(page, limit, sortBy, sort, null, null);
    }

    // Nuovo overload con filtro per gestore
    public ListaRistorantiResponse listRistoranti(int page, int limit, String sortBy, String sort, String search, Long gestoreId) {
        Sort.Direction direction = sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        
        // Per ordinamento per valutazione, recuperiamo TUTTI i ristoranti
        // e li ordiniamo dopo aver calcolato le medie
        boolean sortByRating = "avg_valutazione".equals(sortBy) || "valutazione".equals(sortBy) || "rating".equals(sortBy);
        
        Page<Ristorante> pageRist;
        
        if (sortByRating) {
            // Se ordiniamo per valutazione, prendiamo tutti i ristoranti (con eventuale filtro di ricerca e gestore)
            Pageable pageableAll = PageRequest.of(0, Integer.MAX_VALUE);
            Page<Ristorante> allRistoranti;
            
            if (gestoreId != null) {
                // Filtro per gestore
                if (search != null && !search.trim().isEmpty()) {
                    allRistoranti = ristoranteRepo.findByGestoreIdAndSearchTerm(gestoreId, search.trim(), pageableAll);
                } else {
                    allRistoranti = ristoranteRepo.findByGestoreId(gestoreId, pageableAll);
                }
            } else {
                // Logica esistente senza filtro gestore
                if (search != null && !search.trim().isEmpty()) {
                    allRistoranti = ristoranteRepo.findBySearchTerm(search.trim(), pageableAll);
                } else {
                    allRistoranti = ristoranteRepo.findAll(pageableAll);
                }
            }
            
            // Mappiamo TUTTI i ristoranti in DTO con le medie calcolate
            var allList = allRistoranti.getContent().stream()
                .map(r -> {
                    Double media = recensioneRepo.calcolaMediaValutazione(r.getId_ristorante());
                    Long count = recensioneRepo.contaRecensioni(r.getId_ristorante());
                    return new RistoranteListItemDTO(r, media, count);
                })
                .sorted((a, b) -> {
                    // Ordinamento per valutazione media
                    if (direction == Sort.Direction.DESC) {
                        return Double.compare(b.getAvg_valutazione(), a.getAvg_valutazione());
                    } else {
                        return Double.compare(a.getAvg_valutazione(), b.getAvg_valutazione());
                    }
                })
                .toList();
            
            // Applichiamo la paginazione manualmente
            int totalElements = allList.size();
            int startIndex = (page - 1) * limit;
            int endIndex = Math.min(startIndex + limit, totalElements);
            
            var paginatedList = allList.subList(startIndex, endIndex);
            
            PaginationDTO pagination = new PaginationDTO(page, limit, totalElements);
            return new ListaRistorantiResponse(paginatedList, pagination);
            
        } else {
            // Per altri ordinamenti, usa il metodo normale con eventuale filtro di ricerca e gestore
            String validSortBy;
            switch (sortBy) {
                case "nome":
                case "tipo_cucina":
                case "descrizione":
                    validSortBy = sortBy;
                    break;
                default:
                    validSortBy = "nome"; // fallback sicuro
            }
            
            Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(direction, validSortBy));
            
            if (gestoreId != null) {
                // Filtro per gestore
                if (search != null && !search.trim().isEmpty()) {
                    pageRist = ristoranteRepo.findByGestoreIdAndSearchTerm(gestoreId, search.trim(), pageable);
                } else {
                    pageRist = ristoranteRepo.findByGestoreId(gestoreId, pageable);
                }
            } else {
                // Logica esistente senza filtro gestore
                if (search != null && !search.trim().isEmpty()) {
                    pageRist = ristoranteRepo.findBySearchTerm(search.trim(), pageable);
                } else {
                    pageRist = ristoranteRepo.findAll(pageable);
                }
            }

            // Mappiamo in RistoranteListItemDTO
            var list = pageRist.getContent().stream()
                .map(r -> {
                    Double media = recensioneRepo.calcolaMediaValutazione(r.getId_ristorante());
                    Long count = recensioneRepo.contaRecensioni(r.getId_ristorante());
                    return new RistoranteListItemDTO(r, media, count);
                })
                .toList();

            PaginationDTO pagination = new PaginationDTO(page, limit, pageRist.getTotalElements());
            return new ListaRistorantiResponse(list, pagination);
        }
    }

    // -------- UPDATE ristorante (solo se gestore proprietario)
    public RistoranteResponse updateRistorante(Long idRist, Long idUtente, RistoranteUpdateRequest body) {
        Ristorante r = ristoranteRepo.findById(idRist)
                .orElseThrow(() -> new EntityNotFoundException("Ristorante non trovato"));

        Utente gestore = r.getGestore();
        if (gestore == null || !gestore.getIdUtente().equals(idUtente)) {
            throw new SecurityException("Non sei il gestore di questo ristorante");
        }

        if (body.nome != null) r.setNome(body.nome);
        if (body.tipo_cucina != null) r.setTipo_cucina(body.tipo_cucina);
        if (body.descrizione != null) r.setDescrizione(body.descrizione);
        if (body.rist_img != null) r.setRist_img(body.rist_img);
        if (body.menu_img != null) r.setMenu_img(body.menu_img);

        ristoranteRepo.save(r);

        Double media = recensioneRepo.calcolaMediaValutazione(idRist);
        Long count = recensioneRepo.contaRecensioni(idRist);

        return new RistoranteResponse(r, media, count);
    }

}
