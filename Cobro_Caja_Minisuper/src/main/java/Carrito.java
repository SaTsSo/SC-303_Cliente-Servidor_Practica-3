import java.util.ArrayList;

public class Carrito {

    //Atributos
    private List<Producto> productos;

    //Constructor
    public Carrito(List<Producto> productos) {
        productos = new ArrayList<>();
    }

    //Getter/Setters
    public List<Producto> getProductos() {
        return productos;
    }

    //Metodos
    public void agregarProducto(Producto producto, int cantidad) {
        validarCantidad(cantidad);

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
