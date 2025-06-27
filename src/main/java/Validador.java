public class Validador {
    public static void validandoRangoScanner(int opc, int valorTop) throws ValorFueraRangoExcepcion{
        if (0 > opc || opc > valorTop ){
            throw new ValorFueraRangoExcepcion("El valor ingresado debe estar entre 1 y " + valorTop + ".");
        }
    }
}
