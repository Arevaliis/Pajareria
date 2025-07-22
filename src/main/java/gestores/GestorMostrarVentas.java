package gestores;

import excepciones.ErrorBaseVentasVacia;
import excepciones.ErrorClienteNoExiste;
import excepciones.ErrorNoVentasCliente;
import modelos.Cliente;
import modelos.Venta;
import util.Consola;
import util.Mensajes;
import util.Repetir;
import util.Validador;

import java.util.ArrayList;
import java.util.Scanner;

import util.*;

/**
 * Clase que gestiona muestra historial de ventas.
 *
 * @author Jose Iglesias
 * @version 4.0
 */
public class GestorMostrarVentas {

    /**
     * Muestra el menu de ventas totales para ver el historial de ventas.
     *
     * @param baseClientes ArrayList de clientes
     * @param baseVentas ArrayList de Ventas
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void ejecutarMenuVentasTotales(ArrayList<Cliente> baseClientes, ArrayList<Venta> baseVentas, Scanner scanner){
        try {
            Validador.validarBaseVentasVacia(baseVentas);

            int opc;
            do {
                Mensajes.menuMostrarVentas();
                opc = SelectorOpciones.elegir_opcion(5, scanner);
            } while (opc == -1);

            switch (opc) {
                case 1 -> mostrarVentasTotales(baseVentas);
                case 2 -> mostrarVentasTotalesPorCliente(baseClientes, baseVentas, scanner);
                case 3 -> mostrarImporteTotalPorVenta(baseVentas);
                case 4 -> mostrarImporteTotalVentasPorCliente(baseClientes, baseVentas, scanner);
                case 5 -> {return;}
            }
            seguirMenuMostrarVentas(baseClientes, baseVentas, scanner);
        } catch (ErrorBaseVentasVacia e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Muestra todas las ventas realizadas
     *
     * @param baseVentas ArrayList de Ventas
     */
    public static void mostrarVentasTotales(ArrayList<Venta> baseVentas){
        for (Venta venta: baseVentas){
            Mensajes.ticketCompra(venta);
        }
    }

    /**
     * Obtiene todas las ventas realizadas por un cliente
     *
     * @param baseClientes ArrayList de clientes
     * @param baseVentas ArrayList de Ventas
     * @param scanner Scanner para leer los valores ingresados por el usuario
     *
     * @return ArrayList con las ventas de un cliente si existe, si no {@code null}.
     */
    public static ArrayList<Venta> obtenerVenta(ArrayList<Cliente> baseClientes, ArrayList<Venta> baseVentas, Scanner scanner){
        String dni = Consola.ingresarDni(scanner);
        Cliente cliente = GestorClientes.buscarPorDni(dni, baseClientes);

        try{
            Validador.validarExistenciaCliente(cliente);

            ArrayList<Venta> ventasCliente = new ArrayList<>();
            for (Venta venta : baseVentas) {
                if (venta.getCliente().getDni().equals(cliente.getDni())) {
                    ventasCliente.add(venta);
                }
            }
            Validador.validarVentasClientes(ventasCliente);
            return ventasCliente;
        }catch (ErrorClienteNoExiste | ErrorNoVentasCliente e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Muestra todas las ventas del cliente ingresado
     *
     * @param baseClientes ArrayList de clientes
     * @param baseVentas ArrayList de Ventas
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void mostrarVentasTotalesPorCliente(ArrayList<Cliente> baseClientes, ArrayList<Venta> baseVentas, Scanner scanner){
        ArrayList<Venta> ventasClientes = obtenerVenta(baseClientes, baseVentas, scanner);

        if (ventasClientes == null){ return;}

        for (Venta venta : ventasClientes) {
            Mensajes.ticketCompra(venta);
        }

    }

    /**
     * Muestra el importe total de cada venta
     *
     * @param baseVentas ArrayList de Ventas
     */
    public static void mostrarImporteTotalPorVenta(ArrayList<Venta> baseVentas){
        int contador = 1;
        for (Venta venta: baseVentas){
            Mensajes.mensajeTotalVenta(contador++, venta.getTotal());
        }
    }

    /**
     * Muestra el importe total gastado por el cliente ingresado
     *
     * @param baseClientes ArrayList de clientes
     * @param baseVentas ArrayList de Ventas
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void mostrarImporteTotalVentasPorCliente(ArrayList<Cliente> baseClientes, ArrayList<Venta> baseVentas, Scanner scanner){
        ArrayList<Venta> ventasClientes = obtenerVenta(baseClientes, baseVentas, scanner);

        if (ventasClientes == null){ return;}

        double totalVentas = 0.00;
        String cliente = ventasClientes.getFirst().getCliente().getNombre();

        for (Venta venta : ventasClientes) {
            totalVentas += venta.getTotal();
        }
        Mensajes.importeTotalVentasCliente(cliente, totalVentas);
    }

    /**
     * Pregunta al usuario si desea volver al menu ventas totales.
     * Si ingresa "S" vuelve al menu ventas totales, si no vuelve al menu principal.
     *
     * @param baseClientes ArrayList de clientes
     * @param baseVentas ArrayList de Ventas
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void seguirMenuMostrarVentas(ArrayList<Cliente> baseClientes, ArrayList<Venta> baseVentas, Scanner scanner){
        Mensajes.mensajeVolverMenuVentasTotales();
        if (Repetir.deseaRepetirAccion(scanner)){
            ejecutarMenuVentasTotales(baseClientes, baseVentas, scanner);
        }
    }
}