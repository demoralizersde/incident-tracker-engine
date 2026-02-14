package incidentengine.repository;

import incidentengine.entity.Incident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {

    @Query("""
    SELECT i FROM Incident i
    WHERE (:service IS NULL OR i.service = :service)
      AND (:severity IS NULL OR i.severity = :severity)
      AND (:status IS NULL OR i.status = :status)
    ORDER BY i.id DESC
    """)
    Page<Incident> searchIncidents(
            @Param("service") String service,
            @Param("severity") String severity,
            @Param("status") String status,
            Pageable pageable);

}
