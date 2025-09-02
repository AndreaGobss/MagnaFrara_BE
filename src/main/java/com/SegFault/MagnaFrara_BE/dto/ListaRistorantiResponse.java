package com.SegFault.MagnaFrara_BE.dto;
import java.util.List;

public class ListaRistorantiResponse {
    private List<RistoranteListItemDTO> ristoranti;
    private PaginationDTO pagination;

    public ListaRistorantiResponse(List<RistoranteListItemDTO> ristoranti, PaginationDTO pagination) {
        this.ristoranti = ristoranti;
        this.pagination = pagination;
    }

    // Getters
    public List<RistoranteListItemDTO> getRistoranti() { return ristoranti; }
    public PaginationDTO getPagination() { return pagination; }
}
