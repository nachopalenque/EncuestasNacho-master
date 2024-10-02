package segund.daw.encuestas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import segund.daw.encuestas.Entity.Encuesta;

@Repository
public interface EncuestaRepository extends JpaRepository<Encuesta, Long> {
}
