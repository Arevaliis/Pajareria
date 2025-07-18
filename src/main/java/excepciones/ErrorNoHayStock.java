package excepciones;

/**
 * Mensaje que salta si no hay suficiente stock
 */
public class ErrorNoHayStock extends Exception {
    public ErrorNoHayStock(String message) {
        super(message);
    }
}
