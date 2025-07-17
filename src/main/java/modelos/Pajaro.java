package modelos;

/**
 * Clase que representa a un pájaro
 */
public class Pajaro {
    private String especie;
    private String color;
    private double precio;
    private int stock;

    /**
     * Parámetros para crear un pájaro
     *
     * @param especie Especie del pájaro
     * @param color Color del pájaro
     * @param precio Precio del pájaro
     * @param stock Stock del pájaro
     */
    public Pajaro(String especie, String color, double precio, int stock){
        this.especie = especie;
        this.color = color;
        this.precio = precio;
        this.stock = stock;
    }

    /** @return Especie del pájaro*/
    public String getEspecie() {
        return especie;
    }

    /** @return Color del pájaro*/
    public String getColor() {
        return color;
    }

    /** @return Precio del pájaro*/
    public double getPrecio() {
        return precio;
    }

    /** @return Stock del pájaro */
    public int getStock() {
        return stock;
    }

    /** @param especie Nueva especie para el pájaro */
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    /** @param color Nuevo color para el pájaro */
    public void setColor(String color) {
        this.color = color;
    }

    /** @param precio Nuevo precio para el pájaro */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /** @param stock Nuevo stock para el pájaro */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** @return String con la información del pájaro */
    @Override
    public String toString() {
        return "Especie: " + this.getEspecie() + ", color: " + this.getColor() + ", precio: " + this.getPrecio() + ", stock: " + this.stock;
    }
}