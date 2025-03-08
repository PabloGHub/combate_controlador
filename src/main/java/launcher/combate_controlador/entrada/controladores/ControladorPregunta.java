package launcher.combate_controlador.entrada.controladores;

import launcher.combate_controlador.dtos.DTOcrearPregunta;
import launcher.combate_controlador.dtos.pregunta.DTOpreguntaIndividual;
import launcher.combate_controlador.entrada.servicios.ServiPregunta;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pre")
@AllArgsConstructor
public class ControladorPregunta
{
    private final ServiPregunta serviPregunta;

    @PostMapping("/crear")
    public void crearPregunta(@RequestBody DTOcrearPregunta _pregunta)
    {
        serviPregunta.crearPregunta(_pregunta);
    }

    @GetMapping("/aleatoria")
    public DTOpreguntaIndividual preguntaAleatoria(@RequestParam Integer _idJugador)
    {
        return serviPregunta.preguntaAleatoria(_idJugador);
    }
}
