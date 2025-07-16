import gestores.*;
import util.*;

import java.util.Scanner;

import static util.SelectorOpciones.elegir_opcion;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static boolean estaFuncionando = true;

    public static void main(String[] args) {
        while (estaFuncionando){
            Mensajes.menuInicial();
            int opc = elegir_opcion(5);
            if (opc != -1){
                estaFuncionando = ejecutarOpcion(opc);
            }
        }
    }

// TODO Mostrar importe total de ventas por cliente

    public static boolean ejecutarOpcion(int opc){
        switch (opc){
            case 1 -> GestorClientes.ejecutarMenuCliente();
            case 2 -> GestorPajaros.ejecutarMenuPajaros();
            case 3 -> GestorVentas.iniciarVenta();
            case 4 -> GestorVentas.ejecutarMenuVentasTotales();
            case 5 -> {
                Mensajes.saliendo();
                return false;
            }
        }
        return seguir();
    }

    public static boolean seguir(){
        Mensajes.mensajeVolverMenu();
        if (!deseaRepetirAccion()){
            Mensajes.saliendo();
            return false;
        }
        return true;
    }

    public static boolean deseaRepetirAccion(){
        return scanner.nextLine().trim().equalsIgnoreCase("S");
    }
}