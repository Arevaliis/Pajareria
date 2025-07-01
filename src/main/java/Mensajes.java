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
                    2. Baja de clientes
                    3. Modificación cliente
                    4. Búsqueda por DNI
                    5. Listado
                    6. Volver
                ==============================
                """);
    }

    public static void menuPajaros(){
        System.out.println("""
                
                ======== MENÚ PÁJAROS ========
                    1. Alta de pájaro
                    2. Listado
                    3. Búsqueda por especie
                    4. Volver
                ==============================
                """);
    }

    public static void menuModificarValores(){
        System.out.println("""
                
                ======== CAMPOS CLIENTE ========
                        1. Nombre
                        2. Dni
                        3. Teléfono
                        4. Email
                        5. Volver
                ==============================
                """);
    }

    public static void saliendo(){
        System.out.println("\nSaliendo...");
    }

    public static void clienteEncontrado(){
        System.out.println("Cliente encontrado!");
    }

    public static void clienteNoExiste(){
        System.out.println("No existe ese DNI dentro de la base de datos de clientes.");
    }

    public static void yaExisteDni(){
        System.out.println("Ya existe ese Dni.");
    }

    public static void clienteAgregado(){
        System.out.println("Cliente agregado");
    }

    public static void clienteEliminado(){
        System.out.println("Cliente elilminado");
    }

    public static void agregarOtroCliente(){
        System.out.println("¿Quiere agregar otro cliente? (S/N): ");
    }

    public static void ingresarOtroDni(){
        System.out.print("¿Quieres volver a ingresar un Dni?: (S/N)");
    }

    public static void baseDatosVacia(){
        System.out.println("Base de datos de clientes vacía.");
    }

    public static void mensajeNombre(){
        System.out.print("Ingrese el nombre: ");
    }

    public static void mensajeDni(){
        System.out.print("Ingrese el dni: ");
    }

    public static void mensajeTelefono(){
        System.out.print("Ingrese el teléfono: ");
    }

    public static void mensajeEmail(){
        System.out.print("Ingrese el email: ");
    }

    public static void  emailRepetido(){
        System.out.println("Ya existe el email.\n");
    }

    public static void telefonoRepetido(){
        System.out.println("Ya existe este teléfono.\n");
    }

    public static void probarNuevamente(){
        System.out.print("¿Quiere probar de nuevo? (S/N): ");
    }

    public static void buscarDeNuevo(){
        System.out.print("¿Quieres buscar otro dni?: (S/N)");
    }

    public static void confirmarEliminacion(){
        System.out.print("¿Seguro que quieres eliminarlo? (S/N): ");
    }

    public static void modifcarAlgoMas(){
        System.out.print("¿Quieres modificar algo mas?: (S/N)");
    }

    public static void mensajeVolverMenu(){
        System.out.print("\n¿Quiere volver al menu principal? S/N:");
    }

    public static void mensajeVolverMenuClientes(){
        System.out.print("\n¿Quiere volver al menu clientes? S/N:");
    }

}
