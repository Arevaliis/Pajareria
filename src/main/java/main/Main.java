package main;

import gestores.*;
import modelos.Cliente;
import modelos.Pajaro;
import modelos.Venta;
import util.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase principal que inicia la ejecución del programa.
 *
 * @author Jose Iglesias
 * @version 4.0
 */
public class Main {
    public static ArrayList<Cliente> baseClientes = new ArrayList<>();
    public static ArrayList<Pajaro> basePajaros = new ArrayList<>();
    public static ArrayList<Venta> baseVentas = new ArrayList<>();

    /**
     * Método principal que inicia con el menú principal del programa.
     * Se ejecuta hasta que el usuario decida salir.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean estaFuncionando = true;

        while (estaFuncionando){
            Mensajes.menuInicial();
            int opc = SelectorOpciones.elegir_opcion(5, scanner);
            if (opc != -1){
                estaFuncionando = ejecutarOpcion(opc, scanner);
            }
        }

        scanner.close();
    }

    /**
     * Ejecuta la opción elegida por el usuario.
     *
     * @param opc Valor numérico ingresado por el usuario.
     * @param scanner Scanner para leer los valores ingresados por el usuario.
     *
     * @return Boolean {@code false} para finalizar la ejecución del programa. {@code true} para seguir en el programa.
     */
    public static boolean ejecutarOpcion(int opc, Scanner scanner){
        switch (opc){
            case 1 -> GestorClientes.ejecutarMenuCliente(baseClientes, scanner);
            case 2 -> GestorPajaros.ejecutarMenuPajaros(basePajaros, scanner);
            case 3 -> GestorVentas.iniciarVenta(baseClientes, basePajaros, baseVentas, scanner);
            case 4 -> GestorMostrarVentas.ejecutarMenuVentasTotales(baseClientes, baseVentas, scanner);
            case 5 -> {
                Mensajes.saliendo();
                return false;
            }
        }
        return seguir(scanner);
    }

    /**
     * Pregunta al usuario si desea volver al menú principal tras terminar de ejecutar una opción.
     *
     * @param scanner Scanner para leer los valores ingresados por el usuario.
     *
     * @return {@code true} si el usuario desea continuar en el programa; {@code false} si desea salir.
     */
    public static boolean seguir(Scanner scanner){
        Mensajes.mensajeVolverMenu();
        if (!Repetir.deseaRepetirAccion(scanner)){
            Mensajes.saliendo();
            return false;
        }
        return true;
    }
}