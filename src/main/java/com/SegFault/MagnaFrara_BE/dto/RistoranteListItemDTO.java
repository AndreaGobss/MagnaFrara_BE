package com.SegFault.MagnaFrara_BE.dto;
import com.SegFault.MagnaFrara_BE.entity.Ristorante;
import com.SegFault.MagnaFrara_BE.entity.Recensione;

public class RistoranteListItemDTO {
    private Long id_ristorante;
    private String nome;
    private String rist_img;
    private String tipo_cucina;
    private String descrizione;
    private Double avg_valutazione;
    private Long numero_recensioni;

    public RistoranteListItemDTO(Ristorante r, Double avg, Long numRec) {
        this.id_ristorante = r.getId_ristorante();
        this.nome = r.getNome();
        this.rist_img = r.getRist_img();
        this.tipo_cucina = r.getTipo_cucina();
        this.descrizione = r.getDescrizione();
        this.avg_valutazione = avg != null ? avg : 0.0;
        this.numero_recensioni = numRec != null ? numRec : 0L;
    }

    // Getters
    public Long getId_ristorante() { return id_ristorante; }
    public String getNome() { return nome; }
    public String getRist_img() { return rist_img; }
    public String getTipo_cucina() { return tipo_cucina; }
    public String getDescrizione() { return descrizione; }
    public Double getAvg_valutazione() { return avg_valutazione; }
    public Long getNumero_recensioni() { return numero_recensioni; }
}
