package excepciones;

/**
 * Mensaje de error que se lanza cuando un cliente todavía no ha realizado ninguna compra
 */
public class ErrorNoVentasCliente extends Exception {
    public ErrorNoVentasCliente(String message) {
        super(message);
    }
}