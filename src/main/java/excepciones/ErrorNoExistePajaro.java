package excepciones;

/**
 * Mensaje que salta en el caso de que se busque una especie de pájaro que no está en la base de datos
 */
public class ErrorNoExistePajaro extends Exception {
    public ErrorNoExistePajaro(String message) {
        super(message);
    }
}
