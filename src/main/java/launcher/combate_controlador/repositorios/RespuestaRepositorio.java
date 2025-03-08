package launcher.combate_controlador.repositorios;
import launcher.combate_controlador.modelos.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RespuestaRepositorio extends JpaRepository<Respuesta, Integer>
{

}
