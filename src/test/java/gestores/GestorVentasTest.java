package gestores;

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
        String resp = "PATO\nS\nLORO\n500\nS\nLORO\n-1\n5\nS\nLORO\n6\nS\nLORO\n5\nn\nS";
        Scanner scanner = new Scanner(new ByteArrayInputStream(resp.getBytes()));

        GestorVentas.crearVenta(venta, basePajaros, baseVentas, scanner);
        assertNotEquals(totalVentasAntes, baseVentas.size());
    }

    @Test
    public void calcularPrecioTest(){
        double precio = 2.50;
        int cantidad = 10;
        double resultado = precio * cantidad;

        assertEquals(resultado, GestorVentas.calcularPrecioFinal(2.50, 10));
    }

    @AfterEach
    public void limpiarBases(){
        baseClientes.clear();
        basePajaros.clear();
        baseVentas.clear();
    }
}