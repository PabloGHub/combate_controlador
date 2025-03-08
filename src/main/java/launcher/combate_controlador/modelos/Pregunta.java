package launcher.combate_controlador.modelos;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "pregunta", schema = "supervivencia_preguntas")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pregunta")
    private Integer idPregunta;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType. PERSIST)
    @JoinColumn(name = "id_jugador", nullable = false)
    private Jugador jugador;

    @Column(name = "pregunta", nullable = false)
    private String pregunta;

    @Column(name = "respuesta", nullable = false)
    private String respuesta;

    @Column(name = "fallo1", nullable = false)
    private String fallo1;

    @Column(name = "fallo2", nullable = false)
    private String fallo2;

    @Column(name = "fallo3", nullable = false)
    private String fallo3;

    @Column(name = "ban", nullable = false)
    private Boolean ban;
}
