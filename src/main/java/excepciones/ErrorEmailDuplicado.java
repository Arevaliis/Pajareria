package excepciones;

/**
 * Mensaje de error que se lanza cuando el email ingresado ya existe
 */
public class ErrorEmailDuplicado extends Exception {
    public ErrorEmailDuplicado(String message) {
        super(message);
    }
}
