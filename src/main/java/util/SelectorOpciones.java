package util;

import excepciones.ValorFueraRangoExcepcion;

import java.util.Scanner;

public class SelectorOpciones {
    public static int elegir_opcion(int limite) {
        Scanner scanner = new Scanner(System.in);
        int opc;
        try {
            System.out.print("Elija una opción: ");
            opc = Integer.parseInt(scanner.nextLine().trim());
            Validador.validandoRangoScanner(opc, limite);
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