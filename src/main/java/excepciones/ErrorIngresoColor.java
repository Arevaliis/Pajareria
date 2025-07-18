package excepciones;

/**
 * Mensaje de error que se lanza cuando el color del pájaro agregado no es válido
 */
public class ErrorIngresoColor extends Exception {
    public ErrorIngresoColor(String mensaje){
        super(mensaje);
    }
}