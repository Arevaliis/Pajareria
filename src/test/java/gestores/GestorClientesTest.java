package gestores;

import modelos.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import util.*;

class GestorClientesTest {
    static ArrayList<Cliente> baseClientes = new ArrayList<>();

    @BeforeEach
    public void reinicio(){
        baseClientes.add(new Cliente("JUAN", "45454545F", "654545454", "juan@gmail.com"));
    }

    @Test
    public void buscarPorDniTestExito(){
        assertEquals(baseClientes.getFirst(), GestorClientes.buscarPorDni("45454545F", baseClientes));
    }

    @Test
    public void buscarPorDniTestFallo(){
        assertNull(GestorClientes.buscarPorDni("45454545A", baseClientes));
    }

    @Test
    public void ingresoNombreNoValidoHastaUnoValido() {
        String input = "\n  \n123 \nJUAN"; // Cada salto de línea es una nueva entrada
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        assertEquals("JUAN", Consola.ingresarNombre(scanner));
    }

    @Test
    public void ingresarDniNoValidoHastaUnoValido(){
        String dni = "\n45454 \nasdsaf \n54545454 \n45454545W";
        Scanner scanner = new Scanner(new ByteArrayInputStream(dni.getBytes()));
        assertEquals("45454545W", Consola.ingresarDni(scanner));
    }

    @Test
    public void ingresarTelefonoNoValidoHastaUnoValido(){
        String telefono = "\n \nsdf\n6545454\n654213256";
        Scanner scanner = new Scanner(new ByteArrayInputStream(telefono.getBytes()));
        assertEquals("654213256", Consola.ingresarTelefono(baseClientes, scanner));
    }

    @Test
    public void ingresarEmailNoValidoHastaUnoValido(){
        String email = "\njj.com\n@.com\njj@.cm\njose@yahoo.com";
        Scanner scanner = new Scanner(new ByteArrayInputStream(email.getBytes()));
        assertEquals("jose@yahoo.com", Consola.ingresarEmail(baseClientes, scanner));
    }

    @Test
    public void confirmarCambioDatosClienteTest(){
        String resp = "S";
        Scanner scanner = new Scanner(new ByteArrayInputStream(resp.getBytes()));
        GestorClientes.confirmarCambio("NOMBRE", baseClientes.getFirst(), "PEPE", baseClientes.getFirst().getNombre(), scanner);

        assertEquals("PEPE", baseClientes.getFirst().getNombre());
    }

    @Test
    public void noConfirmarCambioDatosClienteTest(){
        String resp = "N";
        Scanner scanner = new Scanner(new ByteArrayInputStream(resp.getBytes()));
        GestorClientes.confirmarCambio("NOMBRE", baseClientes.getFirst(), "PEPE", baseClientes.getFirst().getNombre(), scanner);

        assertNotEquals("PEPE", baseClientes.getFirst().getNombre());
    }

    @Test
    public void confirmarEliminacionClienteTest(){
        Cliente cliente = new Cliente("JUAN", "45454545F", "654545454", "juan@gmail.com");
        baseClientes.add(cliente);

        String resp = "S";
        Scanner scanner = new Scanner(new ByteArrayInputStream(resp.getBytes()));
        GestorClientes.confirmarEliminacion(cliente, baseClientes, scanner);

        assertFalse(baseClientes.contains(cliente));
    }

    @Test
    public void noConfirmarEliminacionClienteTest(){
        Cliente cliente = new Cliente("JUAN", "45454545F", "654545454", "juan@gmail.com");
        baseClientes.add(cliente);

        String resp = "N";
        Scanner scanner = new Scanner(new ByteArrayInputStream(resp.getBytes()));
        GestorClientes.confirmarEliminacion(cliente, baseClientes, scanner);

        assertTrue(baseClientes.contains(cliente));
    }

    @AfterEach
    public void limpiarBases(){
        baseClientes.clear();
    }
}