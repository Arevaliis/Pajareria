package gestores;

import modelos.Pajaro;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import util.*;
import static org.junit.jupiter.api.Assertions.*;

class GestorPajarosTest {
    static ArrayList<Pajaro> basePajaros = new ArrayList<>();

    @BeforeEach
    public void reinicio(){
        basePajaros.add(new Pajaro("LORO", "VERDE", 5.23, 10));
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

    @Test
    public void busquedaExitosaPajaro(){
        String especie = basePajaros.getFirst().getEspecie();
        Scanner scanner = new Scanner(new ByteArrayInputStream(especie.getBytes()));

        assertEquals(basePajaros.getFirst(), GestorPajaros.busquedaPorEspecie(basePajaros, scanner));
    }

    @Test
    public void busquedaNoExitosaPajaro(){
        String especie = "PATO";
        Scanner scanner = new Scanner(new ByteArrayInputStream(especie.getBytes()));

        assertNull(GestorPajaros.busquedaPorEspecie(basePajaros, scanner));
    }

    @Test
    public void agregarStockNoValidoHastaValido(){
        int stockInicial = basePajaros.getFirst().getStock();
        String resp = "LORO\ns\n100\nS"; // Este método requiere de varios inputs (Especie y luego Cantidad) Asi logramos simularlo.

        Scanner scanner = new Scanner(new ByteArrayInputStream(resp.getBytes()));
        GestorPajaros.agregarStockEspecie(basePajaros, scanner);

        assertNotEquals(stockInicial, basePajaros.getFirst().getStock());
    }

    @AfterEach
    public void limpiarBases(){
        basePajaros.clear();
    }

}