package excepciones;

/**
 * Mensaje de error que se lanza cuando el teléfono ingresado ya existe
 */
public class ErrorTelefonoDuplicado extends Exception {
    public ErrorTelefonoDuplicado(String message) {
        super(message);
    }
}
