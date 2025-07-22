package util;

import modelos.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ConsolaTest {
    static ArrayList<Cliente> baseClientes = new ArrayList<>();

    @BeforeEach
    public void reinicio(){
        baseClientes.add(new Cliente("JUAN", "45454545F", "654545454", "juan@gmail.com"));
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
    public void ingresarEspecieNoValidaHastaLaValida(){
        String especie = "Lo\n2345\nLORO";
        Scanner scanner = new Scanner(new ByteArrayInputStream(especie.getBytes()));

        assertEquals("LORO", Consola.ingresarEspecie(scanner));
    }

    @Test
    public void ingresarColorNoValidaHastaLaValida(){
        String especie = "Lo\n2345\nAZUL";
        Scanner scanner = new Scanner(new ByteArrayInputStream(especie.getBytes()));

        assertEquals("AZUL", Consola.ingresarColor(scanner));
    }

    @Test
    public void ingresarPrecioNoValidaHastaLaValida(){
        String especie = "12,21\nas\n \n12.25";
        Scanner scanner = new Scanner(new ByteArrayInputStream(especie.getBytes()));

        assertEquals(12.25, Consola.ingresarPrecio(scanner));
    }

    @Test
    public void ingresarStockNoValidaHastaLaValida(){
        String especie = "  \n-1\nsa\n12";
        Scanner scanner = new Scanner(new ByteArrayInputStream(especie.getBytes()));

        assertEquals(12, Consola.ingresarStock(scanner));
    }

    @AfterEach
    public void limpiarBases(){
        baseClientes.clear();
    }
}