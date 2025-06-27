import java.util.Scanner;

public class SistemaGestionPajareria {
    static Scanner scanner = new Scanner(System.in);
    static boolean estaFuncionando = true;

    public static void main(String[] args) {
        while (estaFuncionando){
            Mensajes.menuInicial();
            int opc = elegir_opcion(5);
            if (opc != -1){
                estaFuncionando = ejecutarOpcion(opc);
            }
        }
    }

    public static int elegir_opcion(int limite) {
        int opc;
        try {
            System.out.print("Elija una opción: ");
            opc = Integer.parseInt(scanner.nextLine());
            Validador.validandoRangoScanner(opc, limite);
        } catch (NumberFormatException e) {
            System.out.println("\nError -> No puede ingresar valores no numéricos.");
            return -1;
        } catch (ValorFueraRangoExcepcion e) {
            System.out.println("\nError -> " + e.getMessage());
            return -1;
        }
        return opc;
    }

    public static boolean ejecutarOpcion(int opc){
        switch (opc){
            case 1 -> ejecutarMenuCliente();
            case 2 -> ejecutarMenuPajaros();
            case 3 -> System.out.println("3");
            case 4 -> System.out.println("4");
            case 5 -> {
                System.out.println("\nSaliendo...");
                return false;
            }
        }
        return seguir();
    }

    public static void ejecutarMenuCliente(){
        Mensajes.menuClientes();
        int opc = elegir_opcion(5);

        while (opc == -1) {
            Mensajes.menuClientes();
            switch (opc) {
                case 1 -> System.out.println("1");
                case 2 -> System.out.println("2");
                case 3 -> System.out.println("3");
                case 4 -> System.out.println("4");
                case 5 -> System.out.println("5");
            }
            opc = elegir_opcion(5);
        }
    }

    public static void ejecutarMenuPajaros(){
        Mensajes.menuPajaros();
        int opc = elegir_opcion(3);

        while (opc == -1) {
            Mensajes.menuPajaros();
            switch (opc) {
                case 1 -> System.out.println("1");
                case 2 -> System.out.println("2");
                case 3 -> System.out.println("3");
            }
            opc = elegir_opcion(3);
        }
    }

    public static boolean seguir(){
        System.out.print("\n¿Quiere volver al menu? S/N:");
        String resp = scanner.nextLine().strip();
        if (!resp.equalsIgnoreCase("S")){
            System.out.println("Saliendo...");
            return false;
        }
        return true;
    }
}
