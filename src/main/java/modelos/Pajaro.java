package modelos;

public class Pajaro {
    private String especie;
    private String color;
    private double precio;
    private int stock;

    public Pajaro(String especie, String color, double precio, int stock){
        this.especie = especie;
        this.color = color;
        this.precio = precio;
        this.stock = stock;
    }

    public String getEspecie() {
        return especie;
    }

    public String getColor() {
        return color;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Especie: " + this.getEspecie() + ", color: " + this.getColor() + ", precio: " + this.getPrecio() + ", stock: " + this.stock;
    }
}