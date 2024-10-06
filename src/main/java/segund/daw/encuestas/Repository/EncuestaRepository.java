package segund.daw.encuestas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import segund.daw.encuestas.Entity.Encuesta;

import java.util.List;


@Repository
public interface EncuestaRepository extends JpaRepository<Encuesta, Long> {
    List<Encuesta> findByNivelSatisfacion(String nivelSatisfacion);

}
