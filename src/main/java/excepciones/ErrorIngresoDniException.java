package excepciones;

/**
 * Mensaje de error que se lanza cuando el DNI ingresado no cumple con el formato estándar de un DNI (8 números y 1 letra)
 */
public class ErrorIngresoDniException extends Exception{
    public ErrorIngresoDniException(String mensaje){
        super(mensaje);
    }
}