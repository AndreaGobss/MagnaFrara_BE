package com.SegFault.MagnaFrara_BE.dto;

// campi modificabili dal gestore (immagini per ora come stringhe/base64 o URL)
public class RistoranteUpdateRequest {
    public String nome;
    public String tipo_cucina;
    public String descrizione;

    // opzionali: puoi gestirli come URL già hostati, oppure (più avanti) decodificare base64 e salvarli su disco
    public String rist_img; // es: "/uploads/ristoranti/456/main.jpg" oppure base64
    public String menu_img; // es: "/uploads/ristoranti/456/menu.jpg" oppure base64
}
