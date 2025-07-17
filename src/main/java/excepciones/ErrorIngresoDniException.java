package excepciones;

/**
 * Mensaje de error que se lanza cuando el DNI ingresado no cumple con el formato estándar del DNI
 */
public class ErrorIngresoDniException extends Exception{
    public ErrorIngresoDniException(String mensaje){
        super(mensaje);
    }
}