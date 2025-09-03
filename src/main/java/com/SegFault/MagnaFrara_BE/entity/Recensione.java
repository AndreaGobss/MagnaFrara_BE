package com.SegFault.MagnaFrara_BE.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recensione")
public class Recensione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rec;

    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "id_ristorante", nullable = false)
    private Ristorante ristorante;

    private String titolo;
    private String testo;
    private Integer valutazione;

    private LocalDateTime data_pubb;

    // --- GETTER ---
    public Long getId_rec() { return id_rec; }
    public Utente getUtente() { return utente; }
    public Ristorante getRistorante() { return ristorante; }
    public String getTitolo() { return titolo; }
    public String getTesto() { return testo; }
    public Integer getValutazione() { return valutazione; }
    public LocalDateTime getData_pubb() { return data_pubb; }

    // --- SETTER ---
    public void setId_rec(Long id_rec) { this.id_rec = id_rec; }
    public void setUtente(Utente utente) { this.utente = utente; }
    public void setRistorante(Ristorante ristorante) { this.ristorante = ristorante; }
    public void setTitolo(String titolo) { this.titolo = titolo; }
    public void setTesto(String testo) { this.testo = testo; }
    public void setValutazione(Integer valutazione) { this.valutazione = valutazione; }
    public void setData_pubb(LocalDateTime data_pubb) { this.data_pubb = data_pubb; }
}
