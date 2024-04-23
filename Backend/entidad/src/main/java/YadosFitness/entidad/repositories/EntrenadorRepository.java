package YadosFitness.entidad.repositories;
import YadosFitness.entidad.entities.Entrenador;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer> {
    
    @Query( "Select e from Entrenador e where e.id = :nombre" )
    List<Entrenador> findByIdEntrenadorid(@Param("nombre") String nombre);

}