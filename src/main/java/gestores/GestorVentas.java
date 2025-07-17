package gestores;

import excepciones.*;

import modelos.*;
import util.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static gestores.GestorPajaros.*;
import static gestores.GestorClientes.baseClientes;
import static util.SelectorOpciones.elegir_opcion;

public class GestorVentas {
    static ArrayList<Venta> baseVentas = new ArrayList<>();
    public static void iniciarVenta(){
        try {
            Validador.validandoBaseClientes(baseClientes);
            Validador.validandoBasPajaros(basePajaros);
            String dni = GestorClientes.ingresarDni();
            Cliente cliente = GestorClientes.buscarPorDni(dni);
            Validador.validandoExistenciaCliente(cliente);
            crearVenta(new Venta(cliente, new ArrayList<>(), obtenerFecha()));
        }catch (ErrorBaseDatosClientesVacia | ErrorBaseDatosPajarosVacia | ErrorClienteNoExiste e){
            System.out.println(e.getMessage());
        }
    }

    public static void crearVenta(Venta venta){
        boolean seguirAgregando = true;
        double total = 0.00;

        while (seguirAgregando){
            Mensajes.tituloEspecies();
            listarPajaros();
            Mensajes.comprarPajaro();
            Pajaro pajaro = busquedaPorEspecie();

            if(pajaro != null){
                int cantidad = ingresarStock();

                if (pajaro.getStock() - cantidad >= 0){
                    venta.getLineasDeVenta().add(pajaro);
                    total += calcularPrecioFinal(pajaro.getPrecio(), cantidad);
                    pajaro.setStock(pajaro.getStock() - cantidad);

                } else{
                    System.out.println("No hay stock suficiente.");
                }
            }
             else{
                Mensajes.noExistePajaro();
            }

            Mensajes.volverComprarPajaro();
            seguirAgregando = Repetir.deseaRepetirAccion();
        }
        venta.setTotal(total);
        imprimirTicket(venta);
    }

    public static double calcularPrecioFinal(double precio, int cantidad){
        return precio * cantidad;
    }

    public static String obtenerFecha(){
        LocalDate date = LocalDate.now();
        return String.valueOf(date);
    }

    public static void imprimirTicket(Venta venta){
        if (!venta.getLineasDeVenta().isEmpty()){
            Mensajes.compraTotal(venta);
            baseVentas.add(venta);
        }else{
            Mensajes.cestaVacia();
        }
    }

    public static void ejecutarMenuVentasTotales(){
        if (!baseVentas.isEmpty()){
            Mensajes.menuMostarVentas();
            int opc = elegir_opcion(5);

            while (opc == -1) {
                Mensajes.menuMostarVentas();
                opc = elegir_opcion(5);
            }

            switch (opc) {
                case 1 -> mostrarVentasTotales();
                case 2 -> mostrarVentasTotalesPorCliente();
                case 3 -> mostrarImporteTotalPorVenta();
                case 4 -> mostrarImporteTotalVentasPorCliente();
                case 5 -> {
                    return;
                }
            }
            seguirMenuMostrarVentas();
        }else{
            Mensajes.noHayVentas();
        }
    }

    public static void mostrarVentasTotales(){
        for (Venta venta: baseVentas){
            Mensajes.mostrarVentasTotales(venta);
        }
    }

    public static ArrayList<Venta> obtenerVenta(){
        String dni = GestorClientes.ingresarDni();
        Cliente cliente = GestorClientes.buscarPorDni(dni);
        ArrayList<Venta> ventasCliente = new ArrayList<>();

        try{
            Validador.validandoExistenciaCliente(cliente);
            for (Venta venta : baseVentas) {
                if (venta.getCliente().getDni().equals(cliente.getDni())) {
                    ventasCliente.add(venta);
                }
            }
            Validador.validandoVentasClientes(ventasCliente);
            return ventasCliente;
        }catch (ErrorClienteNoExiste | ErrorNoVentasCliente e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static void mostrarVentasTotalesPorCliente(){
        ArrayList<Venta> ventasClientes = obtenerVenta();

        if (ventasClientes != null){
            for (Venta venta : ventasClientes) {
                Mensajes.mostrarVentasTotales(venta);
            }
        }
    }

    public static void mostrarImporteTotalPorVenta(){
        int contador = 1;
        for (Venta venta: baseVentas){
            double total = 0.00;
            for (Pajaro pajaro: venta.getLineasDeVenta()) {
                total += pajaro.getPrecio();
            }
            Mensajes.mensajeTotalVenta(contador++, total);
        }
    }

    public static void mostrarImporteTotalVentasPorCliente(){
        ArrayList<Venta> ventasClientes = obtenerVenta();

        if(ventasClientes != null) {
            double totalVentas = 0.00;
            String cliente = "";

            for (Venta venta : ventasClientes) {
                cliente = venta.getCliente().getNombre();
                for (Pajaro pajaro : venta.getLineasDeVenta()) {
                    totalVentas += pajaro.getPrecio();
                }
            }
            Mensajes.importeTotalVentasCliente(cliente, totalVentas);
        }
    }

    public static void seguirMenuMostrarVentas(){
        Mensajes.mensajeVolverMenuVentasTotales();
        if (Repetir.deseaRepetirAccion()){
            ejecutarMenuVentasTotales();
        }
    }
}