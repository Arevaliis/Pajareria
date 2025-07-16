package util;

import java.util.Scanner;

public class Repetir {
    public static boolean deseaRepetirAccion(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim().equalsIgnoreCase("S");
    }
}