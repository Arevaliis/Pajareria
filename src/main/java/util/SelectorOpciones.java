package util;

import excepciones.ValorFueraRangoExcepcion;

import java.util.Scanner;

/**
 * Clase para seleccionar una opción del menú que esté visualizando el usuario
 */
public class SelectorOpciones {

    /**
     * Solicita al usuario el ingreso de un número dentro del rango del menú mostrado.
     *
     * @param limite Número máximo permitido.
     * @return Opción seleccionada o {@code -1} si ingresa un valor no válido.
     */
    public static int elegir_opcion(int limite) {
        Scanner scanner = new Scanner(System.in);
        int opc;

        try {
            System.out.print("Elija una opción: ");
            opc = Integer.parseInt(scanner.nextLine().trim());
            Validador.validandoRango(opc, limite);
        } catch (NumberFormatException e) {
            System.out.println("\nError -> No puede ingresar valores no numéricos.");
            return -1;
        } catch (ValorFueraRangoExcepcion e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return opc;
    }
}