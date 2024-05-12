package YadosFitness.entidad.dtos;
import java.util.ArrayList;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class DietaDTO{
    private Long id;
    private String nombre;
    private String descripcion;
    private String observaciones;
    private String objetivo;
    private int duracionDias;
    private ArrayList<String> alimentos;
    private String recomendaciones;

}
