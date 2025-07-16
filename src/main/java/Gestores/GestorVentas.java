package Gestores;

import Excepciones.ErrorBaseDatosClientesVacia;
import Excepciones.ErrorBaseDatosPajarosVacia;
import Excepciones.ErrorClienteNoExiste;

import modelos.*;
import util.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static Gestores.GestorPajaros.*;
import static Gestores.GestorClientes.baseClientes;
import static util.SelectorOpciones.elegir_opcion;

// TODO Mostrar importe total de ventas por cliente

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

        while (seguirAgregando){
            Mensajes.tituloEspecies();
            listarPajaros();
            Mensajes.comprarPajaro();
            Pajaro pajaro = busquedaPorEspecie();

            if (pajaro != null){
                venta.getLineasDeVenta().add(pajaro);
            }else{
                Mensajes.noExistePajaro();
            }

            Mensajes.volverComprarPajaro();
            seguirAgregando = Repetir.deseaRepetirAccion();
        }
        imprimirTicket(venta);
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
            int opc = elegir_opcion(4);

            while (opc == -1) {
                Mensajes.menuMostarVentas();
                opc = elegir_opcion(4);
            }

            switch (opc) {
                case 1 -> mostrarVentasTotales();
                case 2 -> mostrarVentasTotalesPorCliente();
                case 3 -> mostrarImporteTotalPorVenta();
                case 4 -> {
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

    public static void mostrarVentasTotalesPorCliente(){
        while (true){
            String dni = GestorClientes.ingresarDni();
            Cliente cliente = GestorClientes.buscarPorDni(dni);

            if (cliente != null){
                for(Venta venta: baseVentas){
                    if (venta.getCliente().getDni().equals(cliente.getDni())){
                        Mensajes.mostrarVentasTotales(venta);
                    }else{
                        Mensajes.clienteNoTieneVentas();
                    }
                }
                return;
            }

            Mensajes.clienteNoExiste();
            Mensajes.ingresarOtroDni();

            if (!scanner.nextLine().equalsIgnoreCase("S")){
                return;
            }
        }
    }

    public static void mostrarImporteTotalPorVenta(){
        int contador = 1;
        for (Venta venta: baseVentas){
            Mensajes.mensajeContador(contador++);
            double total = 0.00;
            for (Pajaro pajaro: venta.getLineasDeVenta()) {
                total += pajaro.getPrecio();
            }
            Mensajes.mensajeTotalVenta(total);
        }
    }

    public static void seguirMenuMostrarVentas(){
        Mensajes.mensajeVolverMenuVentasTotales();
        if (Repetir.deseaRepetirAccion()){
            ejecutarMenuVentasTotales();
        }
    }
}