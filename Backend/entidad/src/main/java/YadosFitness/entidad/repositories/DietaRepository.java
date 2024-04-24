package YadosFitness.entidad.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import YadosFitness.entidad.entities.Dieta;

public interface DietaRepository extends JpaRepository<Dieta, Long> {
    
    @Query( "Select d from Dieta d where d.cliente.getNombre() = :nombre" )
    List<Dieta> findByIdUsuarioid(@Param("nombre") String nombre);

    @Query( "Insert into Dieta(nombre, descripcion, observaciones, objetivos, duracionDias, alimentos, recomendaciones, entrenadorId, clienteId) values(:nombre, :descripcion, :observaciones, :objetivos, :duracionDias, :alimentos, :recomendaciones, :entrenadorId, :clienteId)" )
    void insertDieta(@Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("observaciones") String observaciones, @Param("objetivo") String objetivo, @Param("duracionDias") int duracionDias, @Param("alimentos") ArrayList<String> alimentos, @Param("recomendaciones") String recomendaciones, @Param("entrenadorId") Long entrenadorId, @Param("clienteId") Set<Long> clienteId);

}
