package com.SegFault.MagnaFrara_BE.dto; 

public class GestoreDTO {
    private String nome;
    private String cognome;

    public GestoreDTO(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    // getters
    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
}
