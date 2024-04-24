package YadosFitness.entidad.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import YadosFitness.entidad.entities.Dieta;

public interface DietaRepository extends JpaRepository<Dieta, Long> {

    @Query( "Insert into Dieta(nombre, descripcion, observaciones, objetivos, duracionDias, alimentos, recomendaciones, entrenadorId, clienteId) values(:nombre, :descripcion, :observaciones, :objetivos, :duracionDias, :alimentos, :recomendaciones, :entrenadorId, :clienteId)" )
    void insertDieta(@Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("observaciones") String observaciones, @Param("objetivo") String objetivo, @Param("duracionDias") int duracionDias, @Param("alimentos") ArrayList<String> alimentos, @Param("recomendaciones") String recomendaciones, @Param("entrenadorId") Long entrenadorId, @Param("clienteId") Set<Long> clienteId);
    @Query( "Update Dieta Set nombre=:nombre, descripcion=:descripcion,observaciones=:observaciones,duracionDias=:duracionDias,alimentos=:alimentos,recomendaciones=:recomendaciones,clienteId=:clienteId Where id=:id AND entrenadorId:=entrenadorId")
    void updateDieta(@Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("observaciones") String observaciones, @Param("objetivo") String objetivo, @Param("duracionDias") int duracionDias, @Param("alimentos") ArrayList<String> alimentos, @Param("recomendaciones") String recomendaciones, @Param("entrenadorId") Long entrenadorId, @Param("clienteId") Set<Long> clienteId,@Param("id") Long id);
    @Query( "Update Dieta Set clienteId=:clienteId where id=:id AND entrenadorId:=entrenadorId")
    void updateCliente(@Param("entrenadorId") Long entrenadorId, @Param("clienteId") Set<Long> clienteId,@Param("id") Long id);
    
    List<Dieta> findByCliente(Set<Long> cliente);
    List<Dieta> findByEntrenador(Long entrenador);
    List<Dieta> findByNombre(String nombre);
    
}
