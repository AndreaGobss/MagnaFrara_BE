package com.SegFault.MagnaFrara_BE.repository;
import com.SegFault.MagnaFrara_BE.entity.Ristorante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RistoranteRepository extends JpaRepository<Ristorante, Long> {
    
    @Query("SELECT r FROM Ristorante r WHERE " +
           "LOWER(r.nome) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(r.tipo_cucina) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Ristorante> findBySearchTerm(@Param("search") String search, Pageable pageable);
    
    // Filtro per gestore
    @Query("SELECT r FROM Ristorante r WHERE r.gestore.idUtente = :gestoreId")
    Page<Ristorante> findByGestoreId(@Param("gestoreId") Long gestoreId, Pageable pageable);
    
    // Filtro per gestore con ricerca
    @Query("SELECT r FROM Ristorante r WHERE r.gestore.idUtente = :gestoreId AND " +
           "(LOWER(r.nome) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(r.tipo_cucina) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Ristorante> findByGestoreIdAndSearchTerm(@Param("gestoreId") Long gestoreId, @Param("search") String search, Pageable pageable);
}


/*import com.example.restaurant.entity.Ristorante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RistoranteRepository extends JpaRepository<Ristorante, Long> {
    
    @Query("SELECT r FROM Ristorante r LEFT JOIN FETCH r.gestore WHERE r.id_ristorante = :id")
    Ristorante findByIdWithGestore(@Param("id") Long id);
    
    @Query("SELECT AVG(rec.valutazione) FROM Recensione rec WHERE rec.idRistorante = :ristoranteId")
    Double findAvgValutazioneByRistoranteId(@Param("ristoranteId") Long ristoranteId);
    
    @Query("SELECT COUNT(rec) FROM Recensione rec WHERE rec.idRistorante = :ristoranteId")
    Long countRecensioniByRistoranteId(@Param("ristoranteId") Long ristoranteId);
    
    Page<Ristorante> findAll(Pageable pageable);
}
*/

