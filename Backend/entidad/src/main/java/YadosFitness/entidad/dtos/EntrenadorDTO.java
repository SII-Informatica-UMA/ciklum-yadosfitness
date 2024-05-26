package YadosFitness.entidad.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class EntrenadorDTO {
    Long idUsuario;
    String telefono;
    String direccion;
    String dni;
    String fechaNacimiento;
    String fechaAlta;
    String fechaBaja;
    String especialidad;
    String titulacion;
    String experiencia;
    String observaciones;
    Long id;

}
