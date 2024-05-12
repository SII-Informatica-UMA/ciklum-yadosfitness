package YadosFitness.dtos;
import java.util.ArrayList;
import java.util.Set;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class DietaNuevaDTO {
    private String nombre;
    private String descripcion;
    private String observaciones;
    private String objetivo;
    private int duracionDias;
    private ArrayList<String> alimentos;
    private String recomendaciones;
}
