package com.SegFault.MagnaFrara_BE.service;

import com.SegFault.MagnaFrara_BE.dto.RegisterRequest;
import com.SegFault.MagnaFrara_BE.entity.Utente;
import com.SegFault.MagnaFrara_BE.repository.UtenteRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UtenteRepository repo;

    public AuthService(UtenteRepository repo) { this.repo = repo; }

    public Utente register(RegisterRequest r) {
        Utente u = new Utente();
        u.setNome(r.nome);
        u.setCognome(r.cognome);
        u.setEmail(r.email);
        u.setPassword(r.password); // (in seguito: BCrypt)
        u.setGestore(Boolean.TRUE.equals(r.gestore));
        return repo.save(u);
    }

    public Utente loginByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }
}
