package launcher.combate_controlador.entrada.controladores;

import launcher.combate_controlador.dtos.DTOlistarJugadores;
import launcher.combate_controlador.entrada.servicios.ServiJugador;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/juga")
@AllArgsConstructor
public class ControladorJugador
{
    private final ServiJugador serviJugador;

    @GetMapping("/crear")
    public void crearJugador(@RequestParam String _nombre)
    {
        serviJugador.crearJugador(_nombre);
    }

    @GetMapping("/listar")
    public DTOlistarJugadores listarJugadores()
    {
        return serviJugador.listarJugadores();
    }
}
