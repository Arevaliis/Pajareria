package util;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class SelectorOpcionesTest {

    @Test
    public void elegirBienOpcionTest(){
        String opc = "3";
        Scanner scanner = new Scanner(new ByteArrayInputStream(opc.getBytes()));

        assertEquals(Integer.parseInt(opc), SelectorOpciones.elegir_opcion(6, scanner));
    }

    @Test
    public void elegirMalOpcionTest(){
        String opc = "8";
        Scanner scanner = new Scanner(new ByteArrayInputStream(opc.getBytes()));

        assertNotEquals(Integer.parseInt(opc), SelectorOpciones.elegir_opcion(6, scanner));
    }
}