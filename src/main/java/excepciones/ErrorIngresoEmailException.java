package excepciones;

/**
 * Mensaje de error que se lanza cuando se ingresa un email con un formato inadecuado
 */
public class ErrorIngresoEmailException extends Exception{
    public ErrorIngresoEmailException(String mensaje){
        super(mensaje);
    }
}