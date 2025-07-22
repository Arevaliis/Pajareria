package util;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RepetirTest {

    @Test
    public void deseaRepetirTest(){
        Scanner scanner = new Scanner( new ByteArrayInputStream("S".getBytes())); // Simula la entrada por consola como si el usuario hubiera ingresado "S"
        assertTrue(Repetir.deseaRepetirAccion(scanner));
    }

    @Test
    public void noDeseaRepetirTest(){
        Scanner scanner = new Scanner( new ByteArrayInputStream("N".getBytes()));
        assertFalse(Repetir.deseaRepetirAccion(scanner));
    }
}