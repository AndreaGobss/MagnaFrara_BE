package com.SegFault.MagnaFrara_BE.dto;

import java.util.List;
import java.util.Map;

public class ListaRecensioniResponse {
    public List<RecensioneResponse> recensioni;
    public PaginationDTO pagination;
    
    // Per endpoint recensioni di un ristorante
    public RistoranteInfoDTO ristorante;
    public StatsDTO stats;
    
    // Per endpoint recensioni di un utente
    public UtenteInfoDTO utente;
    
    // Costruttore per recensioni generali
    public ListaRecensioniResponse(List<RecensioneResponse> recensioni, PaginationDTO pagination) {
        this.recensioni = recensioni;
        this.pagination = pagination;
    }
    
    // Costruttore per recensioni di un ristorante (con stats)
    public ListaRecensioniResponse(List<RecensioneResponse> recensioni, PaginationDTO pagination, 
                                   RistoranteInfoDTO ristorante, StatsDTO stats) {
        this.recensioni = recensioni;
        this.pagination = pagination;
        this.ristorante = ristorante;
        this.stats = stats;
    }
    
    // Costruttore per recensioni di un utente
    public ListaRecensioniResponse(List<RecensioneResponse> recensioni, PaginationDTO pagination, 
                                   UtenteInfoDTO utente) {
        this.recensioni = recensioni;
        this.pagination = pagination;
        this.utente = utente;
    }
    
    // DTO per info ristorante
    public static class RistoranteInfoDTO {
        public Long id_ristorante;
        public String nome;
        
        public RistoranteInfoDTO(Long id, String nome) {
            this.id_ristorante = id;
            this.nome = nome;
        }
    }
    
    // DTO per info utente
    public static class UtenteInfoDTO {
        public Long id_utente;
        public String nome;
        public String cognome;
        
        public UtenteInfoDTO(Long id, String nome, String cognome) {
            this.id_utente = id;
            this.nome = nome;
            this.cognome = cognome;
        }
    }
    
    // DTO per statistiche
    public static class StatsDTO {
        public Double avg_valutazione;
        public Map<String, Long> distribuzione_voti;
        
        public StatsDTO(Double avg, Map<String, Long> distribuzione) {
            this.avg_valutazione = avg;
            this.distribuzione_voti = distribuzione;
        }
    }
}
