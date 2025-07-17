package util;

import java.util.Scanner;

/**
 * Clase utilizada para confirmar si el usuario quiere repetir un proceso
 */
public class Repetir {

    /**
     * Permite volver a ejecutar el proceso que acaba de finalizar.
     *
     * @return Boolean True si ingresa S. False si no.
     */
    public static boolean deseaRepetirAccion(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim().equalsIgnoreCase("S");
    }
}