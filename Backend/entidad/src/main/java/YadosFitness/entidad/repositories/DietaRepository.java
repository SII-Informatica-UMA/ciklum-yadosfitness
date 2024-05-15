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
    @Query("INSERT INTO Dieta(nombre, descripcion, observaciones, objetivo, duracionDias, alimentos, recomendaciones, entrenadorId, clienteId) VALUES (:nombre, :descripcion, :observaciones, :objetivo, :duracionDias, :alimentos, :recomendaciones, :entrenadorId, :clienteId)")
    void insertDieta(@Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("observaciones") String observaciones, @Param("objetivo") String objetivo, @Param("duracionDias") int duracionDias, @Param("alimentos") ArrayList<String> alimentos, @Param("recomendaciones") String recomendaciones, @Param("entrenadorId") Long entrenadorId, @Param("clienteId") Set<Long> clienteId);
    @Query("UPDATE Dieta SET nombre = :nombre, descripcion = :descripcion, observaciones = :observaciones, objetivo = :objetivo, duracionDias = :duracionDias, alimentos = :alimentos, recomendaciones = :recomendaciones, clienteId = :clienteId WHERE id = :id AND entrenadorId = :entrenadorId")
    void updateDieta(@Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("observaciones") String observaciones, @Param("objetivo") String objetivo, @Param("duracionDias") int duracionDias, @Param("alimentos") ArrayList<String> alimentos, @Param("recomendaciones") String recomendaciones, @Param("entrenadorId") Long entrenadorId, @Param("clienteId") Set<Long> clienteId,@Param("id") Long id);
    @Query("UPDATE Dieta SET clienteId = :clienteId WHERE id = :id AND entrenadorId = :entrenadorId")
    void updateCliente(@Param("entrenadorId") Long entrenadorId, @Param("clienteId") Set<Long> clienteId,@Param("id") Long id);
    @Query("SELECT d FROM Dieta d WHERE :idCliente MEMBER OF d.clienteId")
    List<Dieta> findByClienteId(Long idCliente);
    //List<Dieta> findByClienteId(Set<Long> clienteId);
    List<Dieta> findByEntrenadorId(Long entrenadorid);
    Optional<Dieta> findByNombre(String nombre);
    List<Dieta> findAllByNombre(String nombre);
    List<Dieta> findAllByEntrenadorId(Long entrenadorId);
}
