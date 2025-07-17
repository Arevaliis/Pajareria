package gestores;

import excepciones.ErrorIngresoColor;
import excepciones.ErrorIngresoNombreException;

import excepciones.ErrorValorInferiorCero;
import modelos.Pajaro;
import util.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Clase que gestiona la funcionalidad de las opciones del menu pájaros
 */
public class GestorPajaros {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Pajaro> basePajaros = new ArrayList<>();

    /**
     * Muestra el menu pájaros hasta que el usuario elija una opción válida
     */
    public static void ejecutarMenuPajaros(){
        int opc;
        do {
            Mensajes.menuPajaros();
            opc = SelectorOpciones.elegir_opcion(5);
            comprobarBaseDatosPajarosVacia(opc);
            seguirMenuPajaros(opc);
        }while (opc == -1);
    }

    /**
     * Comprueba que la base de datos pájaros no se encuentre vacía.
     * Sí es el caso, solo se podrán ejecutar las opciones 1 y 5.
     * Si ingresa otro valor, saltará un mensaje de advertencia.
     *
     * @param opc Número ingresado por el usuario
     */
    public static void comprobarBaseDatosPajarosVacia(int opc){
        if (basePajaros.isEmpty() && opc != 1 && opc != 5 ){
            Mensajes.basePajarosVacia();
        } else {
            ejecutarOpcionMenuPajaros(opc);
        }
    }

    /**
     * Ejecuta la opción elegida por el usuario.
     *
     * @param opc Número ingresado por el usuario.
     */
    public static void ejecutarOpcionMenuPajaros(int opc){
        do {
            switch (opc) {
                case 1 -> agregarPajaro();
                case 2 -> {
                    listarPajaros();
                    return;
                }
                case 3 -> {
                    Pajaro pajaro = busquedaPorEspecie();
                    if (pajaro == null){
                        Mensajes.noExistePajaro();
                    }
                    Mensajes.volverBuscarPajaro();
                }
                case 4 -> agregarStockEspecie();
                case 5 -> {
                    return;
                }
            }
        } while (Repetir.deseaRepetirAccion());
    }

    /**
     * Pide al usuario que ingrese la especie del pájaro
     *
     * @return String con la especie del pájaro
     */
    public static String ingresarEspecie(){
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
     * @return String con el color del pájaro
     */
    public static String ingresarColor(){
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
     * @return double con el precio del pájaro
     */
    public static double ingresarPrecio(){
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
    public static int ingresarStock(){
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
     * Permite agregar un nuevo pájaro
     */
    public static void agregarPajaro(){
        basePajaros.add(new Pajaro(ingresarEspecie(), ingresarColor(), ingresarPrecio(), ingresarStock()));
        Mensajes.agregadoPajaroConExito();
        Mensajes.agregarOtroPajaro();
    }

    /**
     * Busca en la base de datos de pájaros por especie
     * @return Pájaro Si existe la especie, en caso contrario devuelve null
     */
    public static Pajaro busquedaPorEspecie(){
        String especie = ingresarEspecie();
        Mensajes.tipoEspecie(especie);

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
     */
    public static void listarPajaros(){
        basePajaros.sort(Comparator.comparing(Pajaro::getEspecie));

        for (Pajaro pajaro: basePajaros){
            Mensajes.mostrarPajaro(pajaro);
        }
    }

    /**
     * Permite agregar más cantidad al stock de la especie del pájaro que ingresemos.
     */
    public static void agregarStockEspecie(){
        Pajaro pajaro = busquedaPorEspecie();

        if (pajaro != null) {
            int cantidad = ingresarStock();
            pajaro.setStock(pajaro.getStock() + cantidad);
            Mensajes.modificadoStock();
        }else{
            Mensajes.noExistePajaro();
        }

        Mensajes.modificarOtraVezStock();
    }

    /**
     * Pregunta al usuario si desea volver al menu pájaros.
     * Si ingresa "S" vuelve al menu pájaros, si no vuelve al menu principal.
     * Si el usuario selecciono la opción 5 no se ejecuta.
     *
     * @param opc Valor numérico ingresado por el usuario cuando se le solicitó en el menu pájaros
     */
    public static void seguirMenuPajaros(int opc){
        if (opc != 5){
            Mensajes.mensajeVolverMenuPajaros();
            if (Repetir.deseaRepetirAccion()){
                ejecutarMenuPajaros();
            }
        }
    }
}