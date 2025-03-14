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
        System.out.println("\nServiPregunta::banearPregunta");
        System.out.println("_idPregunta: " + _idPregunta);

        Pregunta _pregunta = _repoPregunta.findById(_idPregunta).orElse(null);

        if (_pregunta == null)
            throw new NullPointerException("Controlador:(ServiPregunta->banearPregunta) No se encontro la pregunta.");

        _pregunta.setBan(true);
        if (!compo(_repoPregunta.save(_pregunta)))
            throw new NullPointerException("Controlador:(ServiPregunta->banearPregunta) No se pudo banear la pregunta.");
    }

    public void crearPregunta(DTOcrearPregunta _dto)
    {
        System.out.println("\nServiPregunta::crearPregunta");
        System.out.println("_dto: " + _dto);

        Pregunta _NovoPregunta = desempaquetar(_dto);
        if (!compo(_repoPregunta.save(_NovoPregunta)))
            throw new NullPointerException("Controlador:(ServiPregunta->crearPregunta) No se pudo crear la pregunta.");
    }

    public DTOpreguntaIndividual preguntaAleatoria(Integer _idJugador)
    {
        System.out.println("\nServiPregunta::preguntaAleatoria");
        System.out.println("_idJugador: " + _idJugador);

        List<Pregunta> _preguntas = darmeTodo()
                .stream()
                .filter(p -> !p.getBan())
                .filter(p ->
                {
                    return super._repoRespuesta.findAll()
                            .stream()
                            .filter(r -> r.getJugador().getIdJugador().equals(_idJugador))
                            .filter(r -> r.getAcertada())
                            .noneMatch(r -> r.getPregunta().getIdPregunta().equals(p.getIdPregunta()));
                })
                .toList();

        if (_preguntas.isEmpty())
            return new DTOpreguntaIndividual();

        System.out.println("Preguntas disponibles: " + _preguntas);

        Pregunta _pregunta = _preguntas.get(new Random().nextInt(_preguntas.size()));
        System.out.println("Pregunta seleccionada: " + _pregunta + "\n");
        return empaquetar(_pregunta);
    }
}
