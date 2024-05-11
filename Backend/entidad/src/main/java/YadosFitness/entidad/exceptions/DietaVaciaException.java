package YadosFitness.entidad.exceptions;

public class DietaVaciaException extends RuntimeException{
    public DietaVaciaException(String mensaje){
        super(mensaje);
    }

    public DietaVaciaException(){
        super();
    }
}
