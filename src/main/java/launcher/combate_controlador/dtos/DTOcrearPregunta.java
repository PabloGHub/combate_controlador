package launcher.combate_controlador.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOcrearPregunta
{
    public Integer _idJugador;
    public String _pregunta;
    public String _respuesta;
    public String _fallo1;
    public String _fallo2;
    public String _fallo3;
}
