package com.SegFault.MagnaFrara_BE.repository;
import com.SegFault.MagnaFrara_BE.entity.Recensione;
import com.SegFault.MagnaFrara_BE.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RecensioneRepository extends JpaRepository<Recensione, Long> {
    @Query("SELECT AVG(r.valutazione) FROM Recensione r WHERE r.ristorante.id_ristorante = :idRist")
    Double calcolaMediaValutazione(@Param("idRist") Long idRist);

    @Query("SELECT COUNT(r) FROM Recensione r WHERE r.ristorante.id_ristorante = :idRist")
    Long contaRecensioni(@Param("idRist") Long idRist);
}
