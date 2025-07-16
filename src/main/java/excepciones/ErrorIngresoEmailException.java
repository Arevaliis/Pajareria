package excepciones;

public class ErrorIngresoEmailException extends Exception{
    public ErrorIngresoEmailException(String mensaje){
        super(mensaje);
    }
}
