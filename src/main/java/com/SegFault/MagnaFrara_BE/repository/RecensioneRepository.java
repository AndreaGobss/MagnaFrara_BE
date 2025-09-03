package com.SegFault.MagnaFrara_BE.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SegFault.MagnaFrara_BE.entity.Recensione;

public interface RecensioneRepository extends JpaRepository<Recensione, Long> {
    
    // Query esistenti
    @Query("SELECT AVG(r.valutazione) FROM Recensione r WHERE r.ristorante.id_ristorante = :idRist")
    Double calcolaMediaValutazione(@Param("idRist") Long idRist);

    @Query("SELECT COUNT(r) FROM Recensione r WHERE r.ristorante.id_ristorante = :idRist")
    Long contaRecensioni(@Param("idRist") Long idRist);
    
    // Nuove query per le API
    
    // Trova recensioni per ristorante con paginazione
    @Query("SELECT r FROM Recensione r WHERE r.ristorante.id_ristorante = :idRist")
    Page<Recensione> findByRistoranteId(@Param("idRist") Long idRist, Pageable pageable);
    
    // Trova recensioni per utente con paginazione
    @Query("SELECT r FROM Recensione r WHERE r.utente.idUtente = :idUtente")
    Page<Recensione> findByUtenteId(@Param("idUtente") Long idUtente, Pageable pageable);
    
    // Statistiche distribuzione voti per ristorante
    @Query("SELECT r.valutazione as voto, COUNT(r) as count FROM Recensione r WHERE r.ristorante.id_ristorante = :idRist GROUP BY r.valutazione")
    java.util.List<Object[]> getDistribuzioneVoti(@Param("idRist") Long idRist);
    
    // Media valutazioni per utente
    @Query("SELECT AVG(r.valutazione) FROM Recensione r WHERE r.utente.idUtente = :idUtente")
    Double calcolaMediaValutazioniUtente(@Param("idUtente") Long idUtente);
    
    // Conta recensioni per utente
    @Query("SELECT COUNT(r) FROM Recensione r WHERE r.utente.idUtente = :idUtente")
    Long contaRecensioniUtente(@Param("idUtente") Long idUtente);
}
