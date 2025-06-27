public class Mensajes {
    public static void menuInicial(){
        System.out.println("""
                ======= MENÚ PRINCIPAL =======
                    1. Gestión de clientes
                    2. Gestión de pájaros
                    3. Realizar venta
                    4. Mostrar ventas
                    5. Salir
                ==============================
                """);
    }

    public static void menuClientes(){
        System.out.println("""
                ======= MENÚ CLIENTES ========
                    1. Alta de clientes
                    2. Baja de pájaros
                    3. Modificación cliente
                    4. Búsqueda por DNI
                    5. Listado
                ==============================
                """);
    }

    public static void menuPajaros(){
        System.out.println("""
                ======== MENÚ PÁJAROS ========
                    1. Alta de pájaro
                    2. Listado
                    3. Búsqueda por especie
                ==============================
                """);
    }
}
