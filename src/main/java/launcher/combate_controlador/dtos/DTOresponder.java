package launcher.combate_controlador.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Guardar en respuesta.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOresponder
{
    public Integer _idJugador;
    public Integer _idPregunta;

    // false = respuesta incorrecta, true = respuesta correcta.
    public Boolean _respuesta;
}
