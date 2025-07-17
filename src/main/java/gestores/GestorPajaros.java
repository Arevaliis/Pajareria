package gestores;

import excepciones.ErrorIngresoColor;
import excepciones.ErrorIngresoNombreException;

import excepciones.ErrorValorInferiorCero;
import modelos.Pajaro;
import util.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class GestorPajaros {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Pajaro> basePajaros = new ArrayList<>(
            List.of(
                    new Pajaro("Periquito", "Amarillo", 20.0, 5),
                    new Pajaro("Loro", "Verde", 15.5, 0),
                    new Pajaro("Canario", "Rojo", 120.0, 4)
            )
    );

    public static void ejecutarMenuPajaros(){
        int opc;
        do {
            Mensajes.menuPajaros();
            opc = SelectorOpciones.elegir_opcion(5);
            comprobarBaseDatosPajarosVacia(opc);
            seguirMenuPajaros(opc);
        }while (opc == -1);
    }

    public static void comprobarBaseDatosPajarosVacia(int opc){
        if (basePajaros.isEmpty() && opc != 1 && opc != 5 ){
            Mensajes.basePajarosVacia();
        } else {
            ejecutarOpcionMenuPajaros(opc);
        }
    }

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

    public static void agregarPajaro(){
        basePajaros.add(new Pajaro(ingresarEspecie(), ingresarColor(), ingresarPrecio(), ingresarStock()));
        Mensajes.agregadoPajaroConExito();
        Mensajes.agregarOtroPajaro();
    }

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

    public static void listarPajaros(){
        basePajaros.sort(Comparator.comparing(Pajaro::getEspecie));

        for (Pajaro pajaro: basePajaros){
            Mensajes.mostrarPajaro(pajaro);
        }
    }

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

    public static void seguirMenuPajaros(int opc){
        if (opc != 5){
            Mensajes.mensajeVolverMenuPajaros();
            if (Repetir.deseaRepetirAccion()){
                ejecutarMenuPajaros();
            }
        }
    }
}