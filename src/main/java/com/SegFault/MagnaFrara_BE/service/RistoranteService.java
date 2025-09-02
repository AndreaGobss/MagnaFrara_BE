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

    // -------- LISTA ristoranti con pagination e ordinamento
    public ListaRistorantiResponse listRistoranti(int page, int limit, String sortBy, String sort) {
        Sort.Direction direction = sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(direction, sortBy));
        Page<Ristorante> pageRist = ristoranteRepo.findAll(pageable);

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
