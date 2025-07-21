package gestores;

import excepciones.*;

import modelos.Pajaro;
import util.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Clase que gestiona la funcionalidad de las opciones del menu pájaros,
 * incluyendo agregar pájaro, buscar por especie, mostrar el inventario y agregar stock.
 *
 * @author Jose Iglesias
 * @version 4.0
 */
public class GestorPajaros {

    /**
     * Muestra el menu pájaros hasta que el usuario elija una opción válida
     *
     * @param basePajaros ArrayList de Pájaros
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void ejecutarMenuPajaros(ArrayList<Pajaro> basePajaros, Scanner scanner){
        int opc;
        do {
            Mensajes.menuPajaros();
            opc = SelectorOpciones.elegir_opcion(5, scanner);
            comprobarBaseDatosPajarosVacia(opc, basePajaros, scanner);
            seguirMenuPajaros(opc, basePajaros, scanner);
        }while (opc == -1);
    }

    /**
     * Comprueba que la base de datos pájaros no se encuentre vacía.
     * Sí es el caso, solo se podrán ejecutar las opciones agregar pájaro y volver.
     * Si ingresa otro valor, saltará un mensaje de advertencia.
     *
     * @param opc Número ingresado por el usuario
     * @param basePajaros ArrayList de Pájaros
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void comprobarBaseDatosPajarosVacia(int opc, ArrayList<Pajaro> basePajaros, Scanner scanner){
        if (basePajaros.isEmpty() && opc != 1 && opc != 5 ){
            Mensajes.basePajarosVacia();
        } else {
            ejecutarOpcionMenuPajaros(opc, basePajaros, scanner);
        }
    }

    /**
     * Ejecuta la opción elegida por el usuario.
     *
     * @param opc Número ingresado
     * @param basePajaros ArrayList de Pájaros
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void ejecutarOpcionMenuPajaros(int opc, ArrayList<Pajaro> basePajaros, Scanner scanner){
        do {
            switch (opc) {
                case 1 -> agregarPajaro(basePajaros, scanner);
                case 2 -> {
                    listarPajaros(basePajaros);
                    return;
                }
                case 3 -> {
                    try{
                        Pajaro pajaro = busquedaPorEspecie(basePajaros, scanner);
                        Validador.noExistePajaro(pajaro);
                    } catch (ErrorNoExistePajaro e) {
                        System.out.println(e.getMessage());
                    }

                    Mensajes.volverBuscarPajaro();
                }
                case 4 -> agregarStockEspecie(basePajaros, scanner);
                case 5 -> { return; }
            }
        } while (Repetir.deseaRepetirAccion(scanner));
    }

    /**
     * Pide al usuario que ingrese la especie del pájaro
     *
     * @param scanner Scanner para leer los valores ingresados por el usuario
     * @return String con la especie del pájaro
     */
    public static String ingresarEspecie(Scanner scanner){
        while (true){
            try {
                Mensajes.mensajeEspecie();
                String especie = scanner.nextLine().trim().toUpperCase();
                Validador.validandoNombre(especie);
                return especie;
            }catch (ErrorIngresoNombreException e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Pide al usuario que ingrese el color del pájaro
     *
     * @param scanner Scanner para leer los valores ingresados por el usuario
     * @return String con el color del pájaro
     */
    public static String ingresarColor(Scanner scanner){
        while (true){
            try {
                Mensajes.mensajeColor();
                String color = scanner.nextLine().trim().toUpperCase();
                Validador.validandoColor(color);
                return color;
            } catch (ErrorIngresoColor e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Pide al usuario que ingrese el precio del pájaro
     *
     * @param scanner Scanner para leer los valores ingresados por el usuario
     * @return double con el precio del pájaro
     */
    public static double ingresarPrecio(Scanner scanner){
        while (true){
            try {
                Mensajes.mensajePrecio();
                return (double) Math.round(Double.parseDouble(scanner.nextLine().trim()) * 100) /100;
            } catch (NumberFormatException e) {
                System.out.println("\nError -> Debe ingresar un número. Use punto para los decimales.");
            }
        }
    }

    /**
     * Pide al usuario que ingrese la cantidad disponible
     *
     * @return  int con la cantidad disponible del pájaro
     */
    public static int ingresarStock(Scanner scanner){
        while (true){
           try {
               Mensajes.mensajeStock();
               int cantidad = Integer.parseInt(scanner.nextLine());
               Validador.validandoCantidadStock(cantidad);
               return cantidad;
           }catch (ErrorValorInferiorCero e){
               System.out.println(e.getMessage());
           }catch (NumberFormatException e) {
               System.out.println("\nError -> Debe ingresar un número.");
           }
       }
    }

    /**
     * Comprueba si existe la especie, y si no es así, procede a agregarlo
     *
     * @param basePajaros ArrayList de Pájaros
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void agregarPajaro(ArrayList<Pajaro> basePajaros, Scanner scanner){
        try{
            String especie = ingresarEspecie(scanner);
            Validador.yaExisteEspecie(especie, basePajaros);

            Pajaro pajaro = new Pajaro(especie, ingresarColor(scanner), ingresarPrecio(scanner), ingresarStock(scanner));
            basePajaros.add(pajaro);
            Mensajes.agregadoPajaroConExito();
        } catch (ErrorYaExisteEspecie e) {
            System.out.println(e.getMessage());
        }

        Mensajes.agregarOtroPajaro();
    }

    /**
     * Busca en la base de datos el pájaro por su especie
     *
     * @param basePajaros ArrayList de Pájaros
     * @param scanner Scanner para leer los valores ingresados por el usuario
     * @return {@code Pájaro} Si existe la especie, en caso contrario devuelve {@code null}
     */
    public static Pajaro busquedaPorEspecie(ArrayList<Pajaro> basePajaros, Scanner scanner){
        String especie = ingresarEspecie(scanner);

        for (Pajaro pajaro: basePajaros){
            if(pajaro.getEspecie().equalsIgnoreCase(especie)){
                Mensajes.mostrarPajaro(pajaro);
                return pajaro;
            }
        }
        return null;
    }

    /**
     * Ordena la base de datos de pájaros y la muestra por pantalla.
     *
     * @param basePajaros ArrayList de Pájaros
     */
    public static void listarPajaros(ArrayList<Pajaro> basePajaros){
        basePajaros.sort(Comparator.comparing(Pajaro::getEspecie));

        for (Pajaro pajaro: basePajaros){
            Mensajes.mostrarPajaro(pajaro);
        }
    }

    /**
     * Agrega más cantidad de unidades al stock de la especie del pájaro que ingresemos.
     *
     * @param basePajaros ArrayList de Pájaros
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void agregarStockEspecie(ArrayList<Pajaro> basePajaros, Scanner scanner){
        Pajaro pajaro = busquedaPorEspecie(basePajaros, scanner);

        try {
            Validador.noExistePajaro(pajaro);
            int cantidad = ingresarStock(scanner);
            pajaro.setStock(pajaro.getStock() + cantidad);
            Mensajes.modificadoStock();
        } catch (ErrorNoExistePajaro e) {
            System.out.println(e.getMessage());
        }

        Mensajes.modificarOtraVezStock();
    }

    /**
     * Pregunta al usuario si desea volver al menu pájaros.
     * Si ingresa "S" vuelve al menu pájaros, si no vuelve al menu principal.
     * Si el usuario selecciono la opción 5 no se ejecuta.
     *
     * @param opc Valor numérico ingresado por el usuario en el menu pájaros
     * @param basePajaros ArrayList de Pájaros
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void seguirMenuPajaros(int opc, ArrayList<Pajaro> basePajaros, Scanner scanner ){
        if (opc != 5){
            Mensajes.mensajeVolverMenuPajaros();
            if (Repetir.deseaRepetirAccion(scanner)){
                ejecutarMenuPajaros(basePajaros, scanner);
            }
        }
    }
}