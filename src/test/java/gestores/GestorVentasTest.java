package gestores;

import excepciones.ErrorNoVentasCliente;
import modelos.Cliente;
import modelos.Pajaro;
import modelos.Venta;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static gestores.GestorVentas.*;
import static org.junit.jupiter.api.Assertions.*;

class GestorVentasTest {
    static ArrayList<Venta> baseVentas = new ArrayList<>();
    static ArrayList<Cliente> baseClientes = new ArrayList<>();
    static ArrayList<Pajaro> basePajaros = new ArrayList<>();

    @BeforeEach
    public void reinicio(){
        baseClientes.add(new Cliente("JUAN", "45454545F", "654545454", "jj@jj.com")
        );

        baseClientes.add(
                new Cliente("JOSE", "45454545A", "654454545", "aa@jj.com"));

        basePajaros.add(
                new Pajaro("LORO", "VERDE", 5.23, 10)
        );

        baseVentas.add(new Venta(
                new Cliente("JUAN", "45454545F", "654545454", "jj@jj.com"),
                new ArrayList<>(
                        List.of(new Pajaro("LORO", "VERDE", 5.23, 10))),
                "2025-09-20"
        ));
    }


    @Test
    public void crearVentaNoValidaHastaValida(){
        Venta venta = baseVentas.getFirst();
        int totalVentasAntes = baseVentas.size();
        String resp = "PATO\nS\nLORO\n500\nS\nLORO\n-1\n5\nS\nLORO\n6\nLORO\n5\nn";
        Scanner scanner = new Scanner(new ByteArrayInputStream(resp.getBytes()));

        crearVenta(venta, basePajaros, baseVentas, scanner);
        assertNotEquals(totalVentasAntes, baseVentas.size());
    }

    @Test
    public void ejecutarMenuNoValidoHastaValido(){
        String opc = "6\ndsfs\n-1\n5";
        Scanner scanner = new Scanner(new ByteArrayInputStream(opc.getBytes()));

        ejecutarMenuVentasTotales(baseClientes, baseVentas, scanner);
    }

    @Test
    public void obtenerVentaTest(){
        String resp = "45454545F";
        Scanner scanner = new Scanner(new ByteArrayInputStream(resp.getBytes()));

        assertEquals(1, obtenerVenta(baseClientes, baseVentas, scanner).size());
    }

    @Test
    public void obtenerCompraFallido(){
        String resp = "45454512A";
        Scanner scanner = new Scanner(new ByteArrayInputStream(resp.getBytes()));

        assertNull(obtenerVenta(baseClientes, baseVentas, scanner));
    }


    @Test
    public void obtenerClienteSinCompras(){
        String resp = "45454545A";
        Scanner scanner = new Scanner(new ByteArrayInputStream(resp.getBytes()));

        assertNull(obtenerVenta(baseClientes, baseVentas, scanner));
    }

    @Test
    public void mostrarimportePorClienteInexistente(){
        String resp = "45454512A";
        Scanner scanner = new Scanner(new ByteArrayInputStream(resp.getBytes()));

        mostrarImporteTotalVentasPorCliente(baseClientes, baseVentas, scanner);
    }

    @Test
    public void mostrarVentasPorClienteInexistente(){
        String resp = "45454512A";
        Scanner scanner = new Scanner(new ByteArrayInputStream(resp.getBytes()));

        mostrarVentasTotalesPorCliente(baseClientes, baseVentas, scanner);
    }

    @Test
    public void mostrarImporteTotalDeClienteSinVentas(){
        String resp = "45454545A";
        Scanner scanner = new Scanner(new ByteArrayInputStream(resp.getBytes()));

        mostrarImporteTotalVentasPorCliente(baseClientes, baseVentas, scanner);
    }

    @Test
    public void mostrarVentasTotalesPorClienteConVentas(){
        String resp = "45454545F";
        Scanner scanner = new Scanner(new ByteArrayInputStream(resp.getBytes()));

        mostrarVentasTotalesPorCliente(baseClientes, baseVentas, scanner);
    }

    @Test
    public void mostrarImporteTotalPorClienteConVentas(){
        String resp = "45454545F";
        Scanner scanner = new Scanner(new ByteArrayInputStream(resp.getBytes()));

        mostrarImporteTotalVentasPorCliente(baseClientes, baseVentas, scanner);
    }

    @AfterEach
    public void limpiarBases(){
        baseClientes.clear();
        basePajaros.clear();
        baseVentas.clear();
    }
}