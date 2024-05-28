package YadosFitness.entidad.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import YadosFitness.entidad.entities.Dieta;

public interface DietaRepository extends JpaRepository<Dieta, Long> {
    @Query("SELECT d FROM Dieta d WHERE :idCliente MEMBER OF d.clienteId") //Hay que darle una vuelta a esta query
    List<Dieta> findByClienteId(Long idCliente);
    List<Dieta> findByEntrenadorId(Long entrenadorid);
    Optional<Dieta> findByNombre(String nombre);
    List<Dieta> findAllByNombre(String nombre);
    List<Dieta> findAllByEntrenadorId(Long entrenadorId);
}
