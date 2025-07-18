package excepciones;

/**
 * Mensaje de error que se lanza cuando se ingresa un nombre con algún carácter numérico o un símbolo
 */
public class ErrorIngresoNombreException extends Exception{
    public ErrorIngresoNombreException(String mensaje){
        super(mensaje);
    }
}