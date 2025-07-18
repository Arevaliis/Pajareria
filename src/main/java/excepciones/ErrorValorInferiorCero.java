package excepciones;

/**
 * Mensaje de error que se lanza cuando se ingresa un número inferior a 0
 */
public class ErrorValorInferiorCero extends Exception {
    public ErrorValorInferiorCero(String message) {
        super(message);
    }
}