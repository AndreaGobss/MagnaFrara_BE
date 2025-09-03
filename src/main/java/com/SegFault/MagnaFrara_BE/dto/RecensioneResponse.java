package com.SegFault.MagnaFrara_BE.dto;

import java.time.LocalDateTime;

import com.SegFault.MagnaFrara_BE.entity.Recensione;

public class RecensioneResponse {
    public Long id_rec;
    public Long id_utente;
    public Long id_ristorante;
    public String titolo;
    public String testo;
    public Integer valutazione;
    public LocalDateTime data_pubb;
    
    // Info utente (per quando serve)
    public UtenteDTO utente;
    
    // Info ristorante (per quando serve)
    public RistoranteDTO ristorante;
    
    public RecensioneResponse(Recensione r) {
        this.id_rec = r.getId_rec();
        this.id_utente = r.getUtente().getIdUtente();
        this.id_ristorante = r.getRistorante().getId_ristorante();
        this.titolo = r.getTitolo();
        this.testo = r.getTesto();
        this.valutazione = r.getValutazione();
        this.data_pubb = r.getData_pubb();
        
        // Aggiungi info utente
        this.utente = new UtenteDTO(r.getUtente().getNome(), r.getUtente().getCognome());
        
        // Aggiungi info ristorante
        this.ristorante = new RistoranteDTO(
            r.getRistorante().getNome(), 
            r.getRistorante().getTipo_cucina(),
            r.getRistorante().getRist_img()
        );
    }
    
    // DTO per utente nelle recensioni
    public static class UtenteDTO {
        public String nome;
        public String cognome;
        
        public UtenteDTO(String nome, String cognome) {
            this.nome = nome;
            this.cognome = cognome;
        }
    }
    
    // DTO per ristorante nelle recensioni
    public static class RistoranteDTO {
        public String nome;
        public String tipo_cucina;
        public String rist_img;
        
        public RistoranteDTO(String nome, String tipo_cucina, String rist_img) {
            this.nome = nome;
            this.tipo_cucina = tipo_cucina;
            this.rist_img = rist_img;
        }
    }
}
