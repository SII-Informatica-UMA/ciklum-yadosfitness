package YadosFitness.entidad.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class ClienteDTO {
    
    public enum Sexo {
        MASCULINO,
        FEMENINO,
        OTRO
    }
    
    Long idUsuario;
    String telefono;
    String direccion;
    String dni;
    String fechaNacimiento;
    Sexo sexo;   
    Long id;
}


