package launcher.combate_controlador.repositorios;
import launcher.combate_controlador.modelos.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JugadorRepositorio extends JpaRepository<Jugador, Integer>
{

}
