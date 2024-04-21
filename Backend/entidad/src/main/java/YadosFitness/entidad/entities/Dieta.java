package YadosFitness.entidad.entities;

import java.util.ArrayList;

public class Dieta {
    
    private String nombre;
    private String descripcion;
    private String observaciones;
    private String objetivo;
    private int duracionDias;
    private ArrayList<String> alimentos;
    private String recomendaciones;
    private int id;
    private int usuarioId;
    private int creadorId;

    //Constructor
    public Dieta(String nombre, String descripcion, String observaciones, String objetivo, int durDias, ArrayList<String> alimentos, String recomendaciones, int id, int usuarioId, int creadorId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
        this.objetivo = objetivo;
        this.duracionDias = durDias;
        this.alimentos = alimentos;
        this.recomendaciones = recomendaciones;
        this.id = id;
        this.usuarioId = usuarioId;
        this.creadorId = creadorId;     
    }

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

    public int getUsuarioId() {
        return usuarioId;
    }

    public int getCreadorId() {
        return creadorId;
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

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setCreadorId(int creadorId) {
        this.creadorId = creadorId;
    }
    
}
