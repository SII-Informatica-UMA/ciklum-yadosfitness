package YadosFitness.entidad.entities;

import java.util.List;
import java.util.Objects;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Entrenador {
    @Id
    @GeneratedValue
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private String password;
    @OneToMany(mappedBy = "entrenador", cascade = CascadeType.ALL)
    private List<Dieta> dietas;

    

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Dieta> getDietas() {
        return dietas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, nombre, apellido1, apellido2, email, password, dietas);
    }   

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Entrenador other = (Entrenador) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString(){
        return "El usuario es: " + nombre + " " + apellido1 + " " + apellido2 + ", email: " + email + ", password: " + password + ", dietas: " + dietas.toString();
    }


}
