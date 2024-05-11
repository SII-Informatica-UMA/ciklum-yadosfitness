package YadosFitness.entidad.exceptions;

public class DietaExistException extends RuntimeException{
    public DietaExistException(String s){
        super(s);
    }
    public DietaExistException(){
        super();
    }
}
