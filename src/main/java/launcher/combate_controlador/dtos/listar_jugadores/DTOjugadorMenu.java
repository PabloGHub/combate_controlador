package launcher.combate_controlador.dtos.listar_jugadores;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOjugadorMenu
{
    String _nombre;
    Integer _respuestasCorrectas;
}
