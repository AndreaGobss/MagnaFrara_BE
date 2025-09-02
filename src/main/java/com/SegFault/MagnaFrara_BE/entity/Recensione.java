package com.SegFault.MagnaFrara_BE.entity;
import java.time.LocalDateTime;
import jakarta.persistence.*;

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

    // getters e setters
}
