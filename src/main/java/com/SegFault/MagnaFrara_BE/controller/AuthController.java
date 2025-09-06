package com.SegFault.MagnaFrara_BE.controller;

import com.SegFault.MagnaFrara_BE.dto.LoginRequest;
import com.SegFault.MagnaFrara_BE.dto.RegisterRequest;
import com.SegFault.MagnaFrara_BE.entity.Utente;
import com.SegFault.MagnaFrara_BE.service.AuthService;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) { this.service = service; }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest body) {
        Utente u = service.register(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
            "success", true,
            "data", Map.of(
                "id_utente", u.getIdUtente(),
                "nome", u.getNome(),
                "cognome", u.getCognome(),
                "email", u.getEmail(),
                "gestore", u.getGestore()
            ),
            "message", "Registrazione completata con successo"
        ));
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest body) {
        Utente u = service.loginByEmail(body.email);
        if (u == null || !u.getPassword().equals(body.password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "success", false,
                "error", Map.of(
                    "code", "INVALID_CREDENTIALS",
                    "message", "Email o password non corretti"
                )
            ));
        }
        return ResponseEntity.ok(Map.of(
            "success", true,
            "data", Map.of(
                "id_utente", u.getIdUtente(),
                "nome", u.getNome(),
                "cognome", u.getCognome(),
                "email", u.getEmail(),
                "gestore", u.getGestore()
            ),
            "message", "Login effettuato con successo"
        ));
    }
    @GetMapping
public Map<String, String> test() {
    return Map.of("message", "Auth controller funzionante");
}

}
