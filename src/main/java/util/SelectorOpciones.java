package util;

import excepciones.ValorFueraRangoExcepcion;

import java.util.Scanner;

/**
 * Clase para seleccionar una opción de los menus del programa
 */
public class SelectorOpciones {

    /**
     * Solicita al usuario el ingreso de un numéro dentro del rango del menu mostrado.
     * @param limite Es el numéro máximo permitido.
     * @return Opc puede ser la opción seleccionada o -1.
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