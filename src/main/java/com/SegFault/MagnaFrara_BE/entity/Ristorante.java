package com.SegFault.MagnaFrara_BE.entity;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "ristorante")
public class Ristorante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ristorante;

    private String nome;
    private String rist_img;
    private String menu_img;
    private String tipo_cucina;
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "id_gestore", nullable = false)
    private Utente gestore;

    @OneToMany(mappedBy = "ristorante")
    private List<Recensione> recensioni;

    // --- GETTER ---
    public Long getId_ristorante() { return id_ristorante; }
    public String getNome() { return nome; }
    public String getRist_img() { return rist_img; }
    public String getMenu_img() { return menu_img; }
    public String getTipo_cucina() { return tipo_cucina; }
    public String getDescrizione() { return descrizione; }
    public Utente getGestore() { return gestore; }
    public List<Recensione> getRecensioni() { return recensioni; }

    // --- SETTER ---
    public void setId_ristorante(Long id) { this.id_ristorante = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setRist_img(String rist_img) { this.rist_img = rist_img; }
    public void setMenu_img(String menu_img) { this.menu_img = menu_img; }
    public void setTipo_cucina(String tipo_cucina) { this.tipo_cucina = tipo_cucina; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
    public void setGestore(Utente gestore) { this.gestore = gestore; }
    public void setRecensioni(List<Recensione> recensioni) { this.recensioni = recensioni; }
}
