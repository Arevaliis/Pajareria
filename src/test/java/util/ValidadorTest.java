package util;

import excepciones.*;
import modelos.Cliente;
import modelos.Pajaro;
import modelos.Venta;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidadorTest {
    static ArrayList<Cliente> baseClientes = new ArrayList<>();
    static ArrayList<Pajaro> basePajaros = new ArrayList<>();
    static ArrayList<Venta> baseVentas = new ArrayList<>();

    @BeforeEach
    public void reinicio(){
        Cliente juan = new Cliente("JUAN", "45454545F", "654545454", "juan@gmail.com");
        Pajaro loro = new Pajaro("LORO", "VERDE", 5.23, 10);

        baseClientes.add(juan);
        basePajaros.add(loro);
        baseVentas.add(new Venta(juan, new ArrayList<>(List.of(loro)), "21-02-2025"));
    }
                                    /* ========== Test Rangos ========== */
    @Test
    public void lanzaExcepcionSiValorFueraDeRango() {
        assertThrows(ValorFueraRangoExcepcion.class, () -> Validador.validandoRango(13, 6));
    }

    @Test
    public void mensajeCorrectoValidandoRanggo() {
        ValorFueraRangoExcepcion e = assertThrows(ValorFueraRangoExcepcion.class, () -> Validador.validandoRango(13, 6));

        assertEquals("\nError -> El valor ingresado debe estar entre 1 y 6.", e.getMessage());
    }

                                    /* ========== Test Nombre ========== */

    @Test
    public void lanzaExcepcionSiNombreNoEsValido() {
        assertThrows(ErrorIngresoNombreException.class, () -> Validador.validandoNombre("Jos3"));
    }

    @Test
    public void lanzaExcepcionSiNombreEsCorto() {
        assertThrows(ErrorIngresoNombreException.class, () -> Validador.validandoNombre("Jo"));
    }

    @Test
    public void noLanzaExcepcionNombre() {
        assertDoesNotThrow(() -> Validador.validandoNombre("Jose"));
    }

    @Test
    public void mensajeCorrectoValidandoNombreCorto() {
        ErrorIngresoNombreException e = assertThrows(ErrorIngresoNombreException.class, () -> Validador.validandoNombre("Jo"));

        assertEquals("\nError -> El nombre debe contener al menos 3 letras.", e.getMessage());
    }

    @Test
    public void mensajeCorrectoValidandoNombreNoValidoi() {
        ErrorIngresoNombreException e = assertThrows(ErrorIngresoNombreException.class, () -> Validador.validandoNombre("Jos3"));

        assertEquals("\nError -> El nombre solo puede contener letras.", e.getMessage());
    }

                                    /* ========== Test DNI ========== */

    @Test
    public void lanzaExcepcionSiDniFaltanNumeros() {
        assertThrows(ErrorIngresoDniException.class, () -> Validador.validandoDni("45232F"));
    }

    @Test
    public void lanzaExcepcionSiDniFaltaLetraFinal() {
        assertThrows(ErrorIngresoDniException.class, () -> Validador.validandoDni("45232245"));
    }

    @Test
    public void mensajeCorrectoValidandoDniNumeros() {
        ErrorIngresoDniException e = assertThrows(ErrorIngresoDniException.class, () -> Validador.validandoDni("45232F"));

        assertEquals("\nError -> El DNI debe comenzar con 8 dígitos.", e.getMessage());
    }

    @Test
    public void mensajeCorrectoValidandoDniLetra() {
        ErrorIngresoDniException e = assertThrows(ErrorIngresoDniException.class, () -> Validador.validandoDni("45232545"));

        assertEquals("\nError -> El DNI debe terminar con una letra.", e.getMessage());
    }

    @Test
    public void lanzaExcepcionSiDniDuplicado() {
        assertThrows(ErrorDniDuplicado.class, () -> Validador.validandoDniDuplicado("45454545F", baseClientes));
    }

    @Test
    public void dniNoDuplicadoNoLanzaExcepcion() {
        assertDoesNotThrow(() -> Validador.validandoDniDuplicado("12345678A", baseClientes));
    }

    @Test
    public void mensajeCorrectoDniDuplicado() {
        ErrorDniDuplicado e = assertThrows(ErrorDniDuplicado.class, () -> Validador.validandoDniDuplicado("45454545F", baseClientes));

        assertEquals("\nError: Ya existe un cliente registrado con ese DNI.", e.getMessage());
    }

                                    /* ========== Test Teléfono ========== */

    @Test
    public void lanzaExcepcionMalComienzoTelefono() {
        assertThrows(ErrorIngresoTelefonoException.class, () -> Validador.validandoTelefono("554545454"));
    }

    @Test
    public void lanzaExcepcionTelefonoCorto() {
        assertThrows(ErrorIngresoTelefonoException.class, () -> Validador.validandoTelefono("6545454"));
    }

    @Test
    public void noLanzaExcepcionTelefonoValido() {
        assertDoesNotThrow(() -> Validador.validandoTelefono("654542365"));
    }

    @Test
    public void lanzaExcepcionTelefonoDuplicado() {
        assertThrows(ErrorTelefonoDuplicado.class, () -> Validador.telefonoDuplicado("654545454", baseClientes));
    }

    @Test
    public void noLanzaExcepcionTelefonoDuplicado() {
        assertDoesNotThrow(() -> Validador.telefonoDuplicado("789456123", baseClientes));
    }

    @Test
    public void mensajeCorrectoMalComienzoTelefono() {
        ErrorIngresoTelefonoException e = assertThrows(ErrorIngresoTelefonoException.class, () -> Validador.validandoTelefono("545445454"));

        assertEquals("\nError -> El teléfono debe comenzar por 6, 7, 8 o 9.", e.getMessage());
    }

    @Test
    public void mensajeCorrectoTelefonoCorto() {
        ErrorIngresoTelefonoException e = assertThrows(ErrorIngresoTelefonoException.class, () -> Validador.validandoTelefono("65454"));

        assertEquals("\nError -> El teléfono debe tener exactamente 9 dígitos.", e.getMessage());
    }

    @Test
    public void mensajeCorrectoTelefonoDuplicado() {
        ErrorTelefonoDuplicado e = assertThrows(ErrorTelefonoDuplicado.class, () -> Validador.telefonoDuplicado("654545454", baseClientes));

        assertEquals("\nError: Ya existe un cliente con ese número de teléfono.", e.getMessage());
    }

                                    /* ========== Test Email ========== */

    @Test
    public void lanzaExcepcionNoContieneArroba() {
        assertThrows(ErrorIngresoEmailException.class, () -> Validador.validandoEmail("jjw.com"));
    }

    @Test
    public void lanzaExcepcionMalUsuario() {
        assertThrows(ErrorIngresoEmailException.class, () -> Validador.validandoEmail("@.com"));
    }

    @Test
    public void lanzaExcepcionMalExtension() {
        assertThrows(ErrorIngresoEmailException.class, () -> Validador.validandoEmail("jj@gail.com"));
    }


    @Test
    public void noLanzaExcepcionEmailNoValido() {
        assertDoesNotThrow(() -> Validador.validandoEmail("jose@gmail.com"));
    }

    @Test
    public void lanzaExcepcionEmailDuplicado() {
        assertThrows(ErrorEmailDuplicado.class, () -> Validador.emailDuplicado("juan@gmail.com", baseClientes));
    }

    @Test
    public void noLanzaExcepcionEmailDuplicado() {
        assertDoesNotThrow(() -> Validador.emailDuplicado("jose@gmaill.com", baseClientes));
    }


    @Test
    public void mensajeCorrectoFaltaArrobaEmail() {
        ErrorIngresoEmailException e = assertThrows(ErrorIngresoEmailException.class, () -> Validador.validandoEmail("josehotmail.com"));

        assertEquals("\nError -> El email debe tener @", e.getMessage());
    }

    @Test
    public void mensajeCorrectoMalUsuarioEmail() {
        ErrorIngresoEmailException e = assertThrows(ErrorIngresoEmailException.class, () -> Validador.validandoEmail("@hotmail.com"));

        assertEquals("\nError -> Usuario mal ingresado. Debe contener al menos 1 carácter válido.", e.getMessage());
    }

    @Test
    public void mensajeCorrectoMalExtensionEmail(){
        ErrorIngresoEmailException e = assertThrows(ErrorIngresoEmailException.class, () -> Validador.validandoEmail("jj@yohuu.com"));

        ArrayList<String> dominiosComunes = new ArrayList<>(
                List.of(
                        "@gmail.com",
                        "@hotmail.com",
                        "@outlook.com",
                        "@yahoo.com",
                        "@yahoo.es",
                        "@icloud.com",
                        "@live.com",
                        "@live.es",
                        "@protonmail.com",
                        "@aol.com",
                        "@msn.com",
                        "@gmx.com",
                        "@gmx.es",
                        "@zoho.com",
                        "@mail.com"
                )
        );

        String mensaje = String.join(", ", dominiosComunes);

        assertEquals("\nError -> Dominio no permitido. Debe usar uno de estos dominios: " + mensaje, e.getMessage());
    }

    @Test
    public void mensajeCorrectoEmailDuplicado(){
        ErrorEmailDuplicado e = assertThrows(ErrorEmailDuplicado.class, () -> Validador.emailDuplicado("juan@gmail.com", baseClientes));

        assertEquals("\nError: Ya existe un cliente con ese email.", e.getMessage());
    }

                                    /* ========== Test Colores ========== */

    @Test
    public void lanzaExcepcionNoEsColor(){
        assertThrows(ErrorIngresoColor.class, () -> Validador.validandoColor("PROGRAMA"));
    }

    @Test
    public void noLanzaExcepcionNoEsColor(){
        assertDoesNotThrow(() -> Validador.validandoColor("AZUL"));
    }

    @Test
    public void mensajeCorrectoNoEsColor(){
        ArrayList<String> colores = new ArrayList<>(Arrays.asList(
                "ROJO",
                "AZUL",
                "VERDE",
                "AMARILLO",
                "BLANCO",
                "NEGRO",
                "NARANJA",
                "GRIS",
                "MARRÓN",
                "VIOLETA"
        ));

        String mensaje = "\nError -> Debe ingresar uno de estos colores: " + String.join(", ", colores);
        ErrorIngresoColor e = assertThrows(ErrorIngresoColor.class, () -> Validador.validandoColor("PROGRAMA"));
        assertEquals(mensaje, e.getMessage());
    }

                                    /* ========== Test Clientes ========== */

    @Test
    public void noLanzaErrorSiBaseClientesVacia(){
        assertDoesNotThrow(() -> Validador.validandoBaseClientes(baseClientes));
    }

    @Test
    public void lanzaErrorSiBaseClientesVacia(){
        baseClientes.clear();
        assertThrows(ErrorBaseDatosClientesVacia.class, () -> Validador.validandoBaseClientes(baseClientes)); }

    @Test
    public void mensajeErrorBaseClientesVacia(){
        baseClientes.clear();
        ErrorBaseDatosClientesVacia e = assertThrows(ErrorBaseDatosClientesVacia.class, () -> Validador.validandoBaseClientes(baseClientes));

        assertEquals("\nError -> Hay 0 clientes agregados. Debe ingresar algún cliente para poder hacer una venta.", e.getMessage());
    }

    @Test
    public void lanzaErrorSiClienteYaExiste() {
        Cliente cliente = new Cliente("JUAN", "45454545F", "654545454", "juan@gmail.com");
        assertThrows(ErrorYaExisteCliente.class, () -> Validador.existeCliente(cliente));
    }



                                    /* ========== Test Pájaros ========== */

    @Test
    public void lanzaErrorSiBasePajarosVacia(){
        basePajaros.clear();
        assertThrows(ErrorBaseDatosPajarosVacia.class, () -> Validador.validandoBasePajaros(basePajaros));
    }

    @Test
    public void noLanzaErrorSiBasePajarosVacia(){
        assertDoesNotThrow(() -> Validador.validandoBasePajaros(basePajaros));
    }

    @Test
    public void mensajeErrorBasePajarosVacia(){
        basePajaros.clear();

        ErrorBaseDatosPajarosVacia e = assertThrows(ErrorBaseDatosPajarosVacia.class, () -> Validador.validandoBasePajaros(basePajaros));

        assertEquals("\nError -> Hay 0 especies agregadas. Debe ingresar alguna especie para poder hacer una venta.", e.getMessage());
    }

    @Test
    public void lanzaErrorSiPajaroNoExiste() {
        assertThrows(ErrorNoExistePajaro.class, () -> Validador.noExistePajaro(null));
    }

    @Test
    public void lanzaErrorSiEspecieYaExiste() {
        assertThrows(ErrorYaExisteEspecie.class, () -> Validador.yaExisteEspecie("loro", basePajaros));
    }

                                    /* ========== Test Ventas ========== */

    @Test
    public void lanzaErrorSiClienteNoTieneVentas() {
        baseVentas.clear();
        assertThrows(ErrorNoVentasCliente.class, () -> Validador.validandoVentasClientes(baseVentas));
    }

    @Test
    public void lanzaErrorSiCantidadMenorAUno() {
        assertThrows(ErrorValorInferiorCero.class, () -> Validador.validandoCantidadStock(0));
    }

    @Test
    public void lanzaErrorSiNoHayStockSuficiente() {
        Pajaro pajaro = basePajaros.getFirst();
        assertThrows(ErrorNoHayStock.class, () -> Validador.noHayStock(20, pajaro));
    }

    @Test
    public void lanzaErrorSiCestaVacia() {
        baseVentas.getFirst().getLineasDeVenta().clear();
        assertThrows(ErrorCestaVacia.class, () -> Validador.cestaVacia(baseVentas.getFirst()));
    }

    @Test
    public void noLanzaErrorSiCestaVacia() {
        Venta venta = baseVentas.getFirst();
        assertDoesNotThrow(() -> Validador.cestaVacia(venta));
    }


    @Test
    public void lanzaErrorSiBaseVentasVacia() {
        baseVentas.clear();
        assertThrows(ErrorBaseVentasVacia.class, () -> Validador.baseVentasVacia(baseVentas));

    }

    @AfterEach
    public void limpiarBases(){
        baseClientes.clear();
        basePajaros.clear();
        baseVentas.clear();
    }
}