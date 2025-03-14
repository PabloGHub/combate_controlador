package launcher.combate_controlador.dtos.pregunta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOpreguntaIndividual
{
    public Integer _idPregunta;
    public String _nombreJugador;
    public String _pregunta;
    public List<DTOrespuestaIndividual> _respuestas;
}
