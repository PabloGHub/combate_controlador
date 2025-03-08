package launcher.combate_controlador.repositorios;
import launcher.combate_controlador.modelos.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PreguntaRepositorio extends JpaRepository<Pregunta, Integer>
{

}
