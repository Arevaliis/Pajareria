package main;

import gestores.*;
import util.*;

import static util.Repetir.deseaRepetirAccion;
import static util.SelectorOpciones.elegir_opcion;

/**
 * Clase principal que inicia la ejecución del programa
 *
 * @author Jose Iglesias
 * @version 3.0
 */
public class Main {
    static boolean estaFuncionando = true;

    /**
     * Método principal que inicia con el menú principal del programa.
     * Se ejecuta hasta que el usuario decida salir.
     */
    public static void main(String[] args) {
        while (estaFuncionando){
            Mensajes.menuInicial();
            int opc = elegir_opcion(5);
            if (opc != -1){
                estaFuncionando = ejecutarOpcion(opc);
            }
        }
    }

    /**
     * Ejecuta la opción elegida por el usuario.
     *
     * @param opc Valor numérico ingresado por el usuario.
     * @return Boolean {@code false} para finalizar la ejecución del programa. {@code true} para seguir en el programa.
     */
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

    /**
     * Pregunta al usuario si desea volver al menú principal tras ejecutar una opción.
     *
     * @return {@code true} si el usuario desea continuar en el programa; {@code false} si desea salir.
     */
    public static boolean seguir(){
        Mensajes.mensajeVolverMenu();
        if (!deseaRepetirAccion()){
            Mensajes.saliendo();
            return false;
        }
        return true;
    }
}