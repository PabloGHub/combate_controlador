package launcher.combate_controlador.entrada.servicios;

import launcher.combate_controlador.dtos.DTOcrearPregunta;
import launcher.combate_controlador.modelos.Pregunta;
import launcher.combate_controlador.repositorios.PreguntaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ServiPregunta extends Empaquetador
{
    @Autowired
    private PreguntaRepositorio _repoPregunta;

    public void crearPregunta(DTOcrearPregunta _dto)
    {
        Pregunta _NovoPregunta = desempaquetar(_dto);

        if (!compo(_repoPregunta.save(_NovoPregunta)))
            throw new NullPointerException("Controlador:(ServiPregunta->crearPregunta) No se pudo crear la pregunta.");
    }
}
