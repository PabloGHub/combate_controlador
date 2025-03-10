package launcher.combate_controlador.entrada.servicios;

import launcher.combate_controlador.dtos.pregunta.DTOpreguntaIndividual;
import launcher.combate_controlador.dtos.pregunta.DTOrespuestaIndividual;
import launcher.combate_controlador.modelos.*;
import launcher.combate_controlador.repositorios.*;
import launcher.combate_controlador.dtos.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
@AllArgsConstructor
@NoArgsConstructor
public class Empaquetador
{
    @Autowired
    protected JugadorRepositorio _repoJugador;

    @Autowired
    protected RespuestaRepositorio _repoRespuesta;

    @Autowired
    protected PreguntaRepositorio _repoPregunta;

    //Comprobaciones
    public Boolean compo(Object _dato)
    {
        return switch (_dato)
        {
            case Integer i -> i >= 0;
            case Double v -> v >= 0;
            case Float f -> f >= 0;
            case String s -> !s.isEmpty() && !s.contains("<") && !s.contains(">");
            case null, default -> _dato != null;
        };
    }



    // ****************** Jugador ****************** //


    // ****************** Pregunta ****************** //
    DTOpreguntaIndividual empaquetar(Pregunta _pregunta_o)
    {
        if (!compo(_pregunta_o))
            throw new NullPointerException("Controlador:(Empaquetador:Pregunta) Datos nulos, HDP.");

        if (!compo(_pregunta_o.getIdPregunta()))
            throw new NullPointerException("Controlador:(Empaquetador:Pregunta) getIdPregunta nulos.");

        if (!compo(_pregunta_o.getJugador()))
            throw new NullPointerException("Controlador:(Empaquetador:Pregunta) getJugador nulos.");

        if (!compo(_pregunta_o.getPregunta()))
            throw new NullPointerException("Controlador:(Empaquetador:Pregunta) getPregunta nulos.");

        if (!compo(_pregunta_o.getRespuesta()))
            throw new NullPointerException("Controlador:(Empaquetador:Pregunta) getRespuesta nulos.");

        if (!compo(_pregunta_o.getFallo1()))
            throw new NullPointerException("Controlador:(Empaquetador:Pregunta) getFallo1 nulos.");

        if (!compo(_pregunta_o.getFallo2()))
            throw new NullPointerException("Controlador:(Empaquetador:Pregunta) getFallo2 nulos.");

        if (!compo(_pregunta_o.getFallo3()))
            throw new NullPointerException("Controlador:(Empaquetador:Pregunta) getFallo3 nulos.");

        if (!compo(_pregunta_o.getBan()))
            throw new NullPointerException("Controlador:(Empaquetador:Pregunta) getBan nulos.");


        List<DTOrespuestaIndividual> _listaRespuestas = new ArrayList<>(List.of
        (
                new DTOrespuestaIndividual(_pregunta_o.getRespuesta(), true),
                new DTOrespuestaIndividual(_pregunta_o.getFallo1(), false),
                new DTOrespuestaIndividual(_pregunta_o.getFallo2(), false),
                new DTOrespuestaIndividual(_pregunta_o.getFallo3(), false)
        ));
        Collections.shuffle(_listaRespuestas);


        return new DTOpreguntaIndividual
        (
                _pregunta_o.getIdPregunta(),
                _pregunta_o.getJugador().getNombre(),
                _pregunta_o.getPregunta(),
                _listaRespuestas
        );
    }

    Pregunta desempaquetar(DTOcrearPregunta _dtoCrearPregunta_o)
    {
        if (!compo(_dtoCrearPregunta_o))
            throw new NullPointerException("Controlador:(Empaquetador:DTOcrearPregunta) Datos nulos, HDP.");


        //Pregunta _NovoPregunta = _repoPregunta.findById(_dtoCrearPregunta_o._idPregunta).orElse(null);
        Pregunta _NovoPregunta = new Pregunta();

        _NovoPregunta.setJugador
        (
                (_dtoCrearPregunta_o._idJugador != null) ? _repoJugador.findById(_dtoCrearPregunta_o._idJugador).orElse
                        (
                            (_NovoPregunta.getJugador() != null) ? _NovoPregunta.getJugador() :
                                null
                        ) :
                            (_NovoPregunta.getJugador() != null) ? _NovoPregunta.getJugador() :
                                    null
        );
        if (_NovoPregunta.getJugador() == null)
            throw new NullPointerException("Controlador:(Empaquetador:DTOcrearPregunta) Datos nulos.");

        _NovoPregunta.setPregunta
        (
                (_dtoCrearPregunta_o._pregunta != null) ? _dtoCrearPregunta_o._pregunta :
                        (_NovoPregunta.getPregunta() != null) ? _NovoPregunta.getPregunta() :
                                null
        );
        if (_NovoPregunta.getPregunta() == null)
            throw new NullPointerException("Controlador:(Empaquetador:DTOcrearPregunta) Datos nulos.");

        _NovoPregunta.setRespuesta
        (
                (_dtoCrearPregunta_o._respuesta != null) ? _dtoCrearPregunta_o._respuesta :
                        (_NovoPregunta.getRespuesta() != null) ? _NovoPregunta.getRespuesta() :
                                null
        );
        if (_NovoPregunta.getRespuesta() == null)
            throw new NullPointerException("Controlador:(Empaquetador:DTOcrearPregunta) Datos nulos.");

        _NovoPregunta.setFallo1
        (
                (_dtoCrearPregunta_o._fallo1 != null) ? _dtoCrearPregunta_o._fallo1 :
                        (_NovoPregunta.getFallo1() != null) ? _NovoPregunta.getFallo1() :
                                null
        );
        if (_NovoPregunta.getFallo1() == null)
            throw new NullPointerException("Controlador:(Empaquetador:DTOcrearPregunta) Datos nulos.");

        _NovoPregunta.setFallo2
        (
                (_dtoCrearPregunta_o._fallo2 != null) ? _dtoCrearPregunta_o._fallo2 :
                        (_NovoPregunta.getFallo2() != null) ? _NovoPregunta.getFallo2() :
                                null
        );
        if (_NovoPregunta.getFallo2() == null)
            throw new NullPointerException("Controlador:(Empaquetador:DTOcrearPregunta) Datos nulos.");

        _NovoPregunta.setFallo3
        (
                (_dtoCrearPregunta_o._fallo3 != null) ? _dtoCrearPregunta_o._fallo3 :
                        (_NovoPregunta.getFallo3() != null) ? _NovoPregunta.getFallo3() :
                                null
        );
        if (_NovoPregunta.getFallo3() == null)
            throw new NullPointerException("Controlador:(Empaquetador:DTOcrearPregunta) Datos nulos.");

        _NovoPregunta.setBan(false);

        return _NovoPregunta;
    }


    // ****************** Responder ****************** //
    Respuesta desempaquetar(DTOresponder _dtoresponder_o)
    {
        if (!compo(_dtoresponder_o))
            throw new NullPointerException("Controlador:(Desempaquetador:DTOresponder) Datos nulos, HDP.");

        Jugador _jugador;
        Pregunta _pregunta;


        if (compo(_dtoresponder_o._idJugador))
            _jugador = _repoJugador.findById(_dtoresponder_o._idJugador).orElse(null);
        else
            throw new NullPointerException("Controlador:(Desempaquetador:_dtoresponder_o._idJugador) Datos nulos.");


        if (compo(_dtoresponder_o._idPregunta))
            _pregunta = _repoPregunta.findById(_dtoresponder_o._idPregunta).orElse(null);
        else
            throw new NullPointerException("Controlador:(Desempaquetador:_dtoresponder_o._idPregunta) Datos nulos.");


        if (_jugador == null)
            throw new NullPointerException("Controlador:(Desempaquetador:_jugador) Datos nulos.");

        if (_pregunta == null)
            throw new NullPointerException("Controlador:(Desempaquetador:_pregunta) Datos nulos.");


        return new Respuesta(null, _jugador, _pregunta, _dtoresponder_o._respuesta);
    }
}
