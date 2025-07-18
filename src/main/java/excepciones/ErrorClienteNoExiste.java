package excepciones;

/**
 * Mensaje de error que se lanza cuando se ingresa un DNI que no corresponde con ningún cliente de la base de datos
 */
public class ErrorClienteNoExiste extends Exception {
    public ErrorClienteNoExiste(String message) {
        super(message);
    }
}