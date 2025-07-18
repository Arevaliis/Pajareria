package excepciones;

/**
 * Mensaje de error que se lanza cuando el DNI ingresado ya existe
 */
public class ErrorDniDuplicado extends Exception {
    public ErrorDniDuplicado(String message) {
        super(message);
    }
}
