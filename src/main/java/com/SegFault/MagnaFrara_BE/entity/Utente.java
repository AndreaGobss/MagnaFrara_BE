package com.SegFault.MagnaFrara_BE.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "utente")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utente")
    private Long idUtente;

    private String nome;
    private String cognome;
    private String email;
    private String password;
    private boolean gestore;

    // --- GETTER ---
    public Long getIdUtente() { return idUtente; }
    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public boolean getGestore() { return gestore; }

    // --- SETTER ---
    public void setNome(String nome) { this.nome = nome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setGestore(boolean gestore) { this.gestore = gestore; }
}