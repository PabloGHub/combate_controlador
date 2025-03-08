package launcher.combate_controlador.dtos.pregunta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOrespuestaIndividual
{
    public String _respuesta;
    public Boolean _correcta;
}
