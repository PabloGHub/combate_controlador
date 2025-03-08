package launcher.combate_controlador.entrada.servicios;

import launcher.combate_controlador.dtos.DTOresponder;
import launcher.combate_controlador.modelos.Respuesta;
import launcher.combate_controlador.repositorios.RespuestaRepositorio;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class ServiRespuesta extends Empaquetador
{
    @Autowired
    private RespuestaRepositorio _repoRespuesta;

    public List<Respuesta> darmeTodo()
    {
        return _repoRespuesta.findAll();
    }

    public void responder(DTOresponder _dtoresponder_o)
    {
        Respuesta _respuesta_o = desempaquetar(_dtoresponder_o);
        _respuesta_o = _repoRespuesta.save(_respuesta_o);

        if (_respuesta_o != null)
            throw new NullPointerException("Controlador:(ServiResponder->responder) No se pudo responder.");
    }
}
