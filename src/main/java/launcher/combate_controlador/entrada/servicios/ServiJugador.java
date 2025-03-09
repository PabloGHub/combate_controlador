package launcher.combate_controlador.entrada.servicios;

import launcher.combate_controlador.dtos.listar_jugadores.DTOjugadorMenu;
import launcher.combate_controlador.dtos.listar_jugadores.DTOlistarJugadores;
import launcher.combate_controlador.modelos.Jugador;
import launcher.combate_controlador.modelos.Respuesta;
import launcher.combate_controlador.repositorios.JugadorRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class ServiJugador extends Empaquetador
{
    @Autowired
    private JugadorRepositorio _repoJugador;

    @Autowired
    private ServiRespuesta _serviRespuesta;

    public Integer crearJugador(String _nombre_s)
    {
        System.out.println("\nServiJugador::crearJugador");
        System.out.println("_nombre_s: " + _nombre_s);

        Jugador _jugador = new Jugador(null, _nombre_s);
        _jugador = _repoJugador.save(_jugador);
        if (!compo(_jugador))
            throw new NullPointerException("Controlador:(ServiJugador->crearJugador) No se pudo crear el jugador.");

        return _jugador.getIdJugador();
    }

    public DTOlistarJugadores listarJugadores()
    {
        System.out.println("\nServiJugador::listarJugadores");

        ArrayList<DTOjugadorMenu> _listaJugadores_al = new ArrayList<>();
        List<Jugador> _jugadores = _repoJugador.findAll();

        if (_jugadores.isEmpty())
            throw new NullPointerException("Controlador:(ServiJugador->listarJugadores) No hay jugadores.");

        _jugadores.stream()
                .filter(this::compo)
                .forEach(_individuo ->
                {
                    List<Respuesta> _lista_l = _serviRespuesta.darmeTodo()
                            .stream()
                            .filter(r -> Objects.equals(_individuo.getIdJugador(), _individuo.getIdJugador()))
                            .filter(Respuesta::getAcertada)
                            .toList();

                    _listaJugadores_al.add(new DTOjugadorMenu(_individuo.getNombre(), _lista_l.size()));
                });

        System.out.println("_listaJugadores_al: " + _listaJugadores_al);

        return new DTOlistarJugadores(_listaJugadores_al);
    }
}
