package main.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControlArchivo {

    private static final String ARCHIVO = "inventario.txt";

    public static List<Producto> leerProductos() {

        List<Producto> listaProductos = new ArrayList<>();
        BufferedReader lectura = null;
        String ruta = RutaArchivos.resolver(ARCHIVO);

        try {
            lectura = new BufferedReader(new FileReader(ruta));
            String linea;

            while ((linea = lectura.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",");
                if (datos.length < 4) {
                    System.out.println("Linea invalida (faltan campos): " + linea);
                    continue;
                }
                int codigo = Integer.parseInt(datos[0].trim());
                String nombre = datos[1].trim();
                double precio = Double.parseDouble(datos[2].trim());
                int stock = Integer.parseInt(datos[3].trim());

                Producto producto = new Producto(codigo, nombre, precio, stock);
                listaProductos.add(producto);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Formato de linea invalido: " + e.getMessage());

        } finally {
            cerrarArchivoLectura(lectura);
        }

        return listaProductos;
    }

    public static void cerrarArchivoLectura(BufferedReader archivo) {
        if (archivo == null) {
            return;
        }
        try {
            archivo.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar archivo: " + e.getMessage());
        }
    }
}
