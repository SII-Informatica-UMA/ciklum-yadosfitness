package YadosFitness.entidad.entities;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Dieta {
    
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String descripcion;
    private String observaciones;
    private String objetivo;
    @Column(name = "DURACION_DIAS")
    private int duracionDias;
    @ElementCollection
    private ArrayList<String> alimentos;
    private String recomendaciones;
    @Column(name = "ENTRENADOR_ID", nullable = false)
    private Long entrenadorId;
    @ElementCollection
    @Column(name = "CLIENTE_ID", nullable = false)
    private Set<Long> clienteId;
    

    //Getters
    public String getNombre(){
        return nombre;
    }
    
    public String getDescripcion(){
        return descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public int getDuracionDias() {
        return duracionDias;
    }

    public ArrayList<String> getAlimentos() {
        return alimentos;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public Long getId() {
        return id;
    }

    public Long getEntrenador() {
        return entrenadorId;
    }

    public void setEntrenador(Long entrenadorId) {
        this.entrenadorId = entrenadorId;
    }

    public Set<Long> getCliente() {
        return clienteId;
    }

    public void setCliente(Set<Long> clienteId) {
        this.clienteId = clienteId;
    }
    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public void setDuracionDias(int duracionDias) {
        this.duracionDias = duracionDias;
    }

    public void setAlimentos(ArrayList<String> alimentos) {
        this.alimentos = alimentos;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Dieta other = (Dieta) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(nombre, descripcion, observaciones, objetivo, 
                duracionDias, alimentos, recomendaciones, id, entrenadorId , clienteId);
    }

    @Override
    public String toString(){
        return "La dieta es: "+nombre+ ", descripcion: "+descripcion+", observaciones: "+observaciones
            + ", objetivo: "+objetivo+ ", duracion: "+duracionDias+ "alimentos: "+alimentos.toString()
            +", recomendaciones:"+recomendaciones+", id: "+id+", cliente: "+clienteId.toString()+", entrenador: "+entrenadorId;
    }

}
