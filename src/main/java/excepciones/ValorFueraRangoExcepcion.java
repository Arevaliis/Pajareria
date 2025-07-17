package excepciones;

/**
 * Mensaje de error que se lanza cuando ingresamos un número fuera del rango mostrado
 */
public class ValorFueraRangoExcepcion extends Exception {
    public  ValorFueraRangoExcepcion(String mensaje){
        super(mensaje);
    }
}