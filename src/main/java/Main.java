import gestores.*;
import util.*;

import java.util.Scanner;

import static util.Repetir.deseaRepetirAccion;
import static util.SelectorOpciones.elegir_opcion;

/**
 * Clase principal que inicia la ejecución del programa
 */
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static boolean estaFuncionando = true;

    /**
     * Método principal que inicia el menú principal del programa.
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
     * @return Boolean False para finalizar la ejecución del programa. True para seguir en el programa.
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
     * Permite volver al menu principal al terminar la ejecución de la opción seleccionada anteriormente.
     *
     * @return Boolean False para finalizar la ejecución del programa. True para seguir en el programa.
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