package com.SegFault.MagnaFrara_BE.controller;

import com.SegFault.MagnaFrara_BE.dto.ListaRistorantiResponse;
import com.SegFault.MagnaFrara_BE.dto.RistoranteResponse;
import com.SegFault.MagnaFrara_BE.dto.RistoranteUpdateRequest;
import com.SegFault.MagnaFrara_BE.service.RistoranteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    // GET /api/ristoranti?page=1&limit=10&sortBy=valutazione&sort=desc
    @GetMapping
    public ResponseEntity<?> list(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "sortBy", defaultValue = "nome") String sortBy,
            @RequestParam(name = "sort", defaultValue = "asc") String sort
    ) {
        ListaRistorantiResponse data = ristoranteService.listRistoranti(page, limit, sortBy, sort);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "data", data
        ));
    }
}