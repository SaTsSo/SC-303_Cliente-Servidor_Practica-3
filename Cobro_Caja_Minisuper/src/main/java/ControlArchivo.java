import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ControlArchivo {

    private String ruta = "productos.txt";

    public static List<Producto> leerProductos() {

        List<Producto> listaProductos = new ArrayList<>();

        try {
            DataInputStream lectura = new DataInputStream(new FileInputStream(ruta));

            try {
                while (true) {
                    int codigo = lectura.readInt();
                    String nombre = lectura.readUTF();
                    double precio = lectura.readDouble();
                    int stock = lectura.readInt();

                    Producto producto = new Producto(codigo, nombre, precio, stock);

                    listaProductos.add(producto);
                }

            } catch (EOFException e) {
                cerrarArchivoLectura(lectura);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        }

        return listaProductos;
    }


    public static Producto buscarProducto(int codigoBuscado) {

        List<Producto> productos = leerProductos();

        for (Producto p : productos) {
            if (p.getCodigo() == codigoBuscado) {
                return p;
            }
        }

        return null;
    }



    public static void cerrarArchivoLectura(DataInputStream archivo) {
        try {
            archivo.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar archivo: " + e.getMessage());
        }
    }
}

