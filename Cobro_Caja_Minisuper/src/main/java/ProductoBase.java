package main.java;

public abstract class ProductoBase {

    private int id;
    private String nombre;
    private Double precio;

    //Constructor
    public ProductoBase(int id, String nombre, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }
    public ProductoBase() {
    }

    //Getters/Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    //Metodos
    public abstract double getPrecioFinal();


}
