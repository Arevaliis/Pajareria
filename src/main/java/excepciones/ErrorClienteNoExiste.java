package excepciones;

/**
 * Mensaje de error que se lanza cuando el cliente que se ha ingresado no existe
 */
public class ErrorClienteNoExiste extends Exception {
    public ErrorClienteNoExiste(String message) {
        super(message);
    }
}