package com.SegFault.MagnaFrara_BE.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RecensioneRequest {
    
    @NotNull(message = "ID utente obbligatorio")
    public Long id_utente;
    
    @NotBlank(message = "Titolo obbligatorio")
    @Size(max = 100, message = "Titolo troppo lungo")
    public String titolo;
    
    @NotBlank(message = "Testo recensione obbligatorio")
    @Size(max = 1000, message = "Testo troppo lungo")
    public String testo;
    
    @NotNull(message = "Valutazione obbligatoria")
    @Min(value = 1, message = "Valutazione minima: 1")
    @Max(value = 5, message = "Valutazione massima: 5")
    public Integer valutazione;
}
