package YadosFitness.entidad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import YadosFitness.entidad.entities.Dieta;

public interface DietaRepository extends JpaRepository<Dieta, Integer> {
    
    @Query( "Select d from Dieta d where d.cliente.getNombre() = :nombre" )
    List<Dieta> findByIdUsuarioid(@Param("nombre") String nombre);

}
