package launcher.combate_controlador.entrada.controladores;

import launcher.combate_controlador.dtos.DTOresponder;
import launcher.combate_controlador.entrada.servicios.ServiRespuesta;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/resp")
@AllArgsConstructor
public class ControladorRespuesta
{
    private final ServiRespuesta serviRespuesta;

    @RequestMapping("/responder")
    public void responder(@RequestBody DTOresponder _dtoresponder_o)
    {
        serviRespuesta.responder(_dtoresponder_o);
    }
}
