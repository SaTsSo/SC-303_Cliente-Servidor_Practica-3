package main.java;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

    //Atributos
    private List<Producto> productos;

    //Constructor
    public Carrito() {
        this.productos = new ArrayList<>();
    }

    //Getter/Setters
    public List<Producto> getProductos() {
        return productos;
    }

    //Metodos
    public void agregarProducto(Producto producto, int cantidad) throws StockInsuficienteException {
        validarCantidad(cantidad);
        producto.reducirStock(cantidad);

        for (int i = 0; i < cantidad; i++) {
            productos.add(producto);
        }
    }

    public void validarCantidad(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
    }

}
