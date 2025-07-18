package excepciones;

/**
 * Mensaje que salta si la base de ventas está vacía
 */
public class ErrorBaseVentasVacia extends Exception {
    public ErrorBaseVentasVacia(String message) {
        super(message);
    }
}
