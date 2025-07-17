package excepciones;

/**
 * Mensaje de error que se lanza cuando se ingresa un teléfono con un formato erróneo
 */
public class ErrorIngresoTelefonoException extends Exception{
    public ErrorIngresoTelefonoException(String mensaje){
        super(mensaje);
    }
}