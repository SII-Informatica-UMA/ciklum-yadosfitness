package YadosFitness.entidad.repositories;
import YadosFitness.entidad.entities.Cliente;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
    @Query( "Select c from Cliente c where c.id = :nombre" )
    List<Cliente> findByIdclienteID(@Param("nombre") String nombre);

}

