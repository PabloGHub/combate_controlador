package launcher.combate_controlador.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOlistarJugadores
{
    ArrayList<DTOjugadorMenu> _jugadores = new ArrayList<>();
}