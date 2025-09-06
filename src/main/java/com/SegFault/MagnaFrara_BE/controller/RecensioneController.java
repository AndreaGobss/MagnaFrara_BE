package com.SegFault.MagnaFrara_BE.controller;

import com.SegFault.MagnaFrara_BE.dto.ListaRecensioniResponse;
import com.SegFault.MagnaFrara_BE.dto.RecensioneRequest;
import com.SegFault.MagnaFrara_BE.dto.RecensioneResponse;
import com.SegFault.MagnaFrara_BE.service.RecensioneService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recensioni")
public class RecensioneController {

    private final RecensioneService recensioneService;

    public RecensioneController(RecensioneService recensioneService) {
        this.recensioneService = recensioneService;
    }

    // -------- POST /api/recensioni/{id_rist} - Crea nuova recensione
    @PostMapping("/{id_rist}")
    public ResponseEntity<?> creaRecensione(@PathVariable("id_rist") Long idRistorante,
                                          @Valid @RequestBody RecensioneRequest request) {
        try {
            RecensioneResponse data = recensioneService.creaRecensione(idRistorante, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "success", true,
                    "data", data,
                    "message", "Recensione pubblicata con successo"
            ));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "success", false,
                    "error", Map.of(
                            "code", "NOT_FOUND",
                            "message", e.getMessage()
                    )
            ));
        }
    }

    // -------- GET /api/recensioni/{id_rist} - Recensioni di un ristorante
    @GetMapping("/{id_rist}")
    public ResponseEntity<?> getRecensioniRistorante(
            @PathVariable("id_rist") Long idRistorante,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "sortBy", defaultValue = "data_pubb") String sortBy,
            @RequestParam(name = "sort", defaultValue = "desc") String sort) {
        
        try {
            ListaRecensioniResponse data = recensioneService.getRecensioniRistorante(
                    idRistorante, page, limit, sortBy, sort);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "data", data
            ));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "success", false,
                    "error", Map.of(
                            "code", "NOT_FOUND",
                            "message", e.getMessage()
                    )
            ));
        }
    }

    // -------- GET /api/recensioni/utente/{id_utente} - Recensioni di un utente
    @GetMapping("/utente/{id_utente}")
    public ResponseEntity<?> getRecensioniUtente(
            @PathVariable("id_utente") Long idUtente,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "sortBy", defaultValue = "data_pubb") String sortBy,
            @RequestParam(name = "sort", defaultValue = "desc") String sort) {
        
        try {
            ListaRecensioniResponse data = recensioneService.getRecensioniUtente(
                    idUtente, page, limit, sortBy, sort);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "data", data
            ));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "success", false,
                    "error", Map.of(
                            "code", "NOT_FOUND",
                            "message", e.getMessage()
                    )
            ));
        }
    }

    // -------- GET /api/recensioni - Tutte le recensioni (generale)
    @GetMapping
    public ResponseEntity<?> getAllRecensioni(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "sortBy", defaultValue = "valutazione") String sortBy,
            @RequestParam(name = "sort", defaultValue = "desc") String sort) {
        
        ListaRecensioniResponse data = recensioneService.getAllRecensioni(page, limit, sortBy, sort);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "data", data
        ));
    }
}
