package launcher.combate_controlador.entrada.controladores;

import launcher.combate_controlador.dtos.DTOresponder;
import launcher.combate_controlador.entrada.servicios.ServiRespuesta;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/responder")
@AllArgsConstructor
public class ControladorRespuesta
{
    private final ServiRespuesta serviRespuesta;

    @PostMapping
    public void responder(@RequestBody DTOresponder _respuesta)
    {
        serviRespuesta.responder(_respuesta);
    }
}
