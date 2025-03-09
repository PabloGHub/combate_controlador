package launcher.combate_controlador.entrada.controladores;

import launcher.combate_controlador.dtos.listar_jugadores.DTOlistarJugadores;
import launcher.combate_controlador.entrada.servicios.ServiJugador;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/juga")
@AllArgsConstructor
public class ControladorJugador
{
    private final ServiJugador serviJugador;

    @PostMapping("/crear")
    public Integer crearJugador(@RequestParam String _nombre)
    {
        return serviJugador.crearJugador(_nombre);
    }

    @GetMapping("/listar")
    public DTOlistarJugadores listarJugadores()
    {
        return serviJugador.listarJugadores();
    }
}
