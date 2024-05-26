package YadosFitness.entidad.exceptions;

public class AcessoNoAutorizadoException extends RuntimeException{
    public AcessoNoAutorizadoException(String s){
        super(s);
    }
    public AcessoNoAutorizadoException(){
        super();
    }
}
