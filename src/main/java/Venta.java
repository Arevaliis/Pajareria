import java.util.ArrayList;

public class Venta {
    private Cliente cliente;
    private ArrayList<Pajaro> lineasDeVenta; // cada venta contiene varios pájaros
    private String fecha;

    public Venta(Cliente cliente, ArrayList<Pajaro> lineasDeVenta, String fecha){
        this.cliente = cliente;
        this.lineasDeVenta = lineasDeVenta;
        this.fecha = fecha;
    }
}