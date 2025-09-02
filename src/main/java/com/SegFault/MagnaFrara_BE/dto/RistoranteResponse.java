package com.SegFault.MagnaFrara_BE.dto;
import java.time.LocalDateTime;
import com.SegFault.MagnaFrara_BE.dto.GestoreDTO;
import com.SegFault.MagnaFrara_BE.entity.Ristorante;

public class RistoranteResponse {
    private Long id_ristorante;
    private String nome;
    private String rist_img;
    private String menu_img;
    private String tipo_cucina;
    private String descrizione;
    private Double avg_valutazione;
    private Long numero_recensioni;
    private Long id_gestore;
    private GestoreDTO gestore;
    private LocalDateTime created_at;

    // Costruttore per mappare direttamente da entity + dati calcolati
    public RistoranteResponse(Ristorante r, Double avg, Long numRec) {
        this.id_ristorante = r.getId_ristorante();
        this.nome = r.getNome();
        this.rist_img = r.getRist_img();
        this.menu_img = r.getMenu_img();
        this.tipo_cucina = r.getTipo_cucina();
        this.descrizione = r.getDescrizione();
        this.avg_valutazione = avg != null ? avg : 0.0;
        this.numero_recensioni = numRec != null ? numRec : 0L;
        this.id_gestore = r.getGestore().getIdUtente();
        this.gestore = new GestoreDTO(r.getGestore().getNome(), r.getGestore().getCognome());
        this.created_at = LocalDateTime.now(); // se vuoi salvare nel DB puoi prendere da entity
    }

    // getters e setters
    public Long getId_ristorante() { return id_ristorante; }
    public String getNome() { return nome; }
    public String getRist_img() { return rist_img; }
    public String getMenu_img() { return menu_img; }
    public String getTipo_cucina() { return tipo_cucina; }
    public String getDescrizione() { return descrizione; }
    public Double getAvg_valutazione() { return avg_valutazione; }
    public Long getNumero_recensioni() { return numero_recensioni; }
    public Long getId_gestore() { return id_gestore; }
    public GestoreDTO getGestore() { return gestore; }
    public LocalDateTime getCreated_at() { return created_at; }
}
