package com.SegFault.MagnaFrara_BE.controller;

import com.SegFault.MagnaFrara_BE.dto.ListaRistorantiResponse;
import com.SegFault.MagnaFrara_BE.dto.RistoranteResponse;
import com.SegFault.MagnaFrara_BE.dto.RistoranteUpdateRequest;
import com.SegFault.MagnaFrara_BE.service.RistoranteService;
import jakarta.persistence.EntityNotFoundException;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ristoranti")
public class RistoranteController {

    private final RistoranteService ristoranteService;

    public RistoranteController(RistoranteService ristoranteService) {
        this.ristoranteService = ristoranteService;
    }

    // GET /api/ristoranti/{id_ristorante}
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            RistoranteResponse data = ristoranteService.getRistorante(id);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "data", data
            ));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "success", false,
                    "error", Map.of(
                            "code", "NOT_FOUND",
                            "message", "Ristorante non trovato"
                    )
            ));
        }
    }

    // GET /api/ristoranti?page=1&limit=10&sortBy=valutazione&sort=desc&search=pizza&gestore_id=1
    @GetMapping
    public ResponseEntity<?> list(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "sortBy", defaultValue = "nome") String sortBy,
            @RequestParam(name = "sort", defaultValue = "asc") String sort,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "gestore_id", required = false) Long gestoreId
    ) {
        ListaRistorantiResponse data = ristoranteService.listRistoranti(page, limit, sortBy, sort, search, gestoreId);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "data", data
        ));
    }

    // PUT /ristoranti/{id}?id_utente={idUtente}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRistorante(
            @PathVariable("id") Long id,
            @RequestParam("id_utente") Long idUtente,
            @RequestBody RistoranteUpdateRequest updateData
    ) {
        try {
            RistoranteResponse data = ristoranteService.updateRistorante(id, idUtente, updateData);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "data", data,
                    "message", "Ristorante aggiornato con successo"
            ));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "success", false,
                    "error", Map.of(
                            "code", "NOT_FOUND",
                            "message", "Ristorante non trovato"
                    )
            ));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of(
                    "success", false,
                    "error", Map.of(
                            "code", "FORBIDDEN",
                            "message", e.getMessage()
                    )
            ));
        }
    }
}