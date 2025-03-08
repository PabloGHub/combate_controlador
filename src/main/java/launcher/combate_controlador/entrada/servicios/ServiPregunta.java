package launcher.combate_controlador.entrada.servicios;

import launcher.combate_controlador.dtos.DTOcrearPregunta;
import launcher.combate_controlador.dtos.pregunta.DTOpreguntaIndividual;
import launcher.combate_controlador.modelos.Pregunta;
import launcher.combate_controlador.repositorios.PreguntaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
@AllArgsConstructor
public class ServiPregunta extends Empaquetador
{
    @Autowired
    private PreguntaRepositorio _repoPregunta;

    private ServiRespuesta _serviRespuesta;


    public Pregunta darmeUna(Integer _idPregunta)
    {
        return _repoPregunta.findById(_idPregunta).orElse(null);
    }

    public List<Pregunta> darmeTodo()
    {
        return _repoPregunta.findAll();
    }

    public void banearPregunta(Integer _idPregunta)
    {
        Pregunta _pregunta = darmeUna(_idPregunta);

        if (_pregunta == null)
            throw new NullPointerException("Controlador:(ServiPregunta->banearPregunta) No se encontro la pregunta.");

        _pregunta.setBan(true);
        if (!compo(_repoPregunta.save(_pregunta)))
            throw new NullPointerException("Controlador:(ServiPregunta->banearPregunta) No se pudo banear la pregunta.");
    }

    public void crearPregunta(DTOcrearPregunta _dto)
    {
        Pregunta _NovoPregunta = desempaquetar(_dto);

        if (!compo(_repoPregunta.save(_NovoPregunta)))
            throw new NullPointerException("Controlador:(ServiPregunta->crearPregunta) No se pudo crear la pregunta.");
    }

    public DTOpreguntaIndividual preguntaAleatoria(Integer _idJugador)
    {
        List<Pregunta> _preguntas = darmeTodo()
                .stream()
                .filter(p -> !p.getBan())
                .filter(p ->
                {
                    return _serviRespuesta.darmeTodo()
                            .stream()
                            .filter(r -> r.getJugador().getIdJugador().equals(_idJugador))
                            .filter(r -> r.getAcertada())
                            .noneMatch(r -> r.getPregunta().getIdPregunta().equals(p.getIdPregunta()));
                })
                .toList();

        if (_preguntas.isEmpty())
            throw new NullPointerException("No hay preguntas disponibles.");

        Pregunta _pregunta = _preguntas.get(new Random().nextInt(_preguntas.size()));
        return empaquetar(_pregunta);
    }
}
