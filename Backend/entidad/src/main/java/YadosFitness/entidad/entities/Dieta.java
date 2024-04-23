package YadosFitness.entidad.entities;

import java.util.ArrayList;
import java.util.Objects;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Dieta {
    
    private String nombre;
    private String descripcion;
    private String observaciones;
    private String objetivo;
    private int duracionDias;
    @ElementCollection
    private ArrayList<String> alimentos;
    private String recomendaciones;
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "entrenadorID", nullable = false)
    private Entrenador entrenador;
    @ManyToOne
    @JoinColumn(name = "clienteID", nullable = false)
    private Cliente cliente;
    

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

    public int getId() {
        return id;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
                duracionDias, alimentos, recomendaciones, id, entrenador , cliente);
    }

    @Override
    public String toString(){
        return "La dieta es: "+nombre+ ", descripcion: "+descripcion+", observaciones: "+observaciones
            + ", objetivo: "+objetivo+ ", duracion: "+duracionDias+ "alimentos: "+alimentos.toString()
            +", recomendaciones:"+recomendaciones+", id: "+id+", cliente: "+cliente.getNombre()+", entrenador: "+entrenador.getNombre();
    }

}
