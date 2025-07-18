package excepciones;

/**
 * Mensaje de error que se lanza cuando el cliente ingresado ya existe
 */
public class ErrorYaExisteCliente extends Exception {
    public ErrorYaExisteCliente(String message) {
        super(message);
    }
}