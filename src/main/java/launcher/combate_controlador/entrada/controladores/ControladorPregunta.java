package launcher.combate_controlador.entrada.controladores;

import launcher.combate_controlador.dtos.DTOcrearPregunta;
import launcher.combate_controlador.dtos.pregunta.DTOpreguntaIndividual;
import launcher.combate_controlador.entrada.servicios.ServiPregunta;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/pre")
@AllArgsConstructor
public class ControladorPregunta
{
    private final ServiPregunta serviPregunta;

    @RequestMapping("/crear")
    public void crearPregunta(@RequestBody DTOcrearPregunta _pregunta)
    {
        serviPregunta.crearPregunta(_pregunta);
    }

    @RequestMapping("/aleatoria")
    public DTOpreguntaIndividual preguntaAleatoria(@RequestParam Integer _idJugador)
    {
        return serviPregunta.preguntaAleatoria(_idJugador);
    }
}
