package com.SegFault.MagnaFrara_BE.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.SegFault.MagnaFrara_BE.dto.RegisterRequest;
import com.SegFault.MagnaFrara_BE.entity.Utente;
import com.SegFault.MagnaFrara_BE.repository.UtenteRepository;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UtenteRepository utenteRepository;

    @InjectMocks
    private AuthService authService;

    private RegisterRequest registerRequest;
    private Utente mockUtente;

    @BeforeEach
    void setUp() {
        // Setup dati di test
        registerRequest = new RegisterRequest();
        registerRequest.nome = "Mario";
        registerRequest.cognome = "Rossi";
        registerRequest.email = "mario.rossi@test.com";
        registerRequest.password = "password123";
        registerRequest.gestore = false;

        mockUtente = new Utente();
        mockUtente.setNome("Mario");
        mockUtente.setCognome("Rossi");
        mockUtente.setEmail("mario.rossi@test.com");
        mockUtente.setPassword("password123");
        mockUtente.setGestore(false);
    }

    @Test
    void register_ShouldCreateUtente_WhenValidRequest() {
        // Given
        when(utenteRepository.save(any(Utente.class))).thenReturn(mockUtente);

        // When
        Utente result = authService.register(registerRequest);

        // Then
        assertNotNull(result);
        assertEquals("Mario", result.getNome());
        assertEquals("Rossi", result.getCognome());
        assertEquals("mario.rossi@test.com", result.getEmail());
        assertEquals("password123", result.getPassword());
        assertFalse(result.getGestore());

        verify(utenteRepository, times(1)).save(any(Utente.class));
    }

    @Test
    void register_ShouldCreateGestore_WhenGestoreIsTrue() {
        // Given
        registerRequest.gestore = true;
        mockUtente.setGestore(true);
        when(utenteRepository.save(any(Utente.class))).thenReturn(mockUtente);

        // When
        Utente result = authService.register(registerRequest);

        // Then
        assertTrue(result.getGestore());
        verify(utenteRepository, times(1)).save(any(Utente.class));
    }

    @Test
    void register_ShouldHandleNullGestore_AsNormalUser() {
        // Given
        registerRequest.gestore = null;
        when(utenteRepository.save(any(Utente.class))).thenReturn(mockUtente);

        // When
        Utente result = authService.register(registerRequest);

        // Then
        assertFalse(result.getGestore());
        verify(utenteRepository, times(1)).save(any(Utente.class));
    }

    @Test
    void loginByEmail_ShouldReturnUtente_WhenEmailExists() {
        // Given
        String email = "mario.rossi@test.com";
        when(utenteRepository.findByEmail(email)).thenReturn(Optional.of(mockUtente));

        // When
        Utente result = authService.loginByEmail(email);

        // Then
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(utenteRepository, times(1)).findByEmail(email);
    }

    @Test
    void loginByEmail_ShouldReturnNull_WhenEmailNotExists() {
        // Given
        String email = "nonexistent@test.com";
        when(utenteRepository.findByEmail(email)).thenReturn(Optional.empty());

        // When
        Utente result = authService.loginByEmail(email);

        // Then
        assertNull(result);
        verify(utenteRepository, times(1)).findByEmail(email);
    }

    @Test
    void checkPassword_ShouldReturnTrue_WhenPasswordsMatch() {
        // Given
        String rawPassword = "password123";
        String storedPassword = "password123";

        // When
        boolean result = authService.checkPassword(rawPassword, storedPassword);

        // Then
        assertTrue(result);
    }

    @Test
    void checkPassword_ShouldReturnFalse_WhenPasswordsDontMatch() {
        // Given
        String rawPassword = "wrongpassword";
        String storedPassword = "password123";

        // When
        boolean result = authService.checkPassword(rawPassword, storedPassword);

        // Then
        assertFalse(result);
    }

    @Test
    void checkPassword_ShouldReturnFalse_WhenNullPasswords() {
        // When & Then
        assertFalse(authService.checkPassword(null, "password123"));
        assertFalse(authService.checkPassword("password123", null));
        assertFalse(authService.checkPassword(null, null));
    }
}