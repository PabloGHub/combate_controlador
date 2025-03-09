package launcher.combate_controlador.entrada.servicios;

import launcher.combate_controlador.dtos.DTOresponder;
import launcher.combate_controlador.modelos.Respuesta;
import launcher.combate_controlador.repositorios.RespuestaRepositorio;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class ServiRespuesta extends Empaquetador
{
    @Autowired
    private RespuestaRepositorio _repoRespuesta;

    @Autowired
    private ServiPregunta _serviPregunta;

    public List<Respuesta> darmeTodo()
    {
        return _repoRespuesta.findAll();
    }

    public Respuesta darmeUna(Integer _idPregunta)
    {
        return _repoRespuesta.findById(_idPregunta).orElse(null);
    }

    public void responder(DTOresponder _dtoresponder_o)
    {
        System.out.println("\nServiRespuesta::responder");

        Respuesta _respuesta_o = desempaquetar(_dtoresponder_o);
        _respuesta_o = _repoRespuesta.save(_respuesta_o);

        if (_respuesta_o == null)
            throw new NullPointerException("Controlador:(ServiResponder->responder) No se pudo responder.");

        Respuesta final_respuesta_o1 = _respuesta_o;
        Long _cantidadAcertadas_long = _repoRespuesta.findAll()
                .stream()
                .filter(r -> r.getPregunta().getIdPregunta().equals(final_respuesta_o1.getPregunta().getIdPregunta()))
                .filter(r -> r.getAcertada())
                .count();
        System.out.println("_cantidadAcertadas_long: " + _cantidadAcertadas_long);

        Respuesta final_respuesta_o = _respuesta_o;
        Long _cantidadFallidas_long = _repoRespuesta.findAll()
                .stream()
                .filter(r -> r.getPregunta().getIdPregunta().equals(final_respuesta_o.getPregunta().getIdPregunta()))
                .filter(r -> !r.getAcertada())
                .count();
        System.out.println("_cantidadFallidas_long: " + _cantidadFallidas_long);


        long _cantidadTotal_long = _cantidadAcertadas_long + _cantidadFallidas_long;
        System.out.println("_cantidadTotal_long: " + _cantidadTotal_long);


        if (_cantidadTotal_long > 6)
        {
            float _PorAcertadas_f = (float) _cantidadAcertadas_long / _cantidadTotal_long;
            float _PorFallidas_f = (float) _cantidadFallidas_long / _cantidadTotal_long;

            System.out.println("_PorAcertadas_f: " + _PorAcertadas_f);
            System.out.println("_PorFallidas_f: " + _PorFallidas_f);

            if (_serviPregunta == null)
                throw new NullPointerException("Controlador:(ServiResponder->responder) PORQUE _serviPregunta ES NULL!!.");

            if (_PorAcertadas_f > 0.66f || _PorFallidas_f > 0.66f)
                _serviPregunta.banearPregunta(_respuesta_o.getPregunta().getIdPregunta());
        }

    }
}
