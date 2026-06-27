package main.java;

import java.io.FileWriter;
import java.io.PrintWriter;

public class CrearInventarioTxt {
    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(new FileWriter("inventario.txt"));

        escribir(out, 101, "Leche entera 1L", 18.50, 50);
        escribir(out, 102, "Pan blanco", 12.00, 30);
        escribir(out, 103, "Arroz 1kg", 25.00, 40);
        escribir(out, 104, "Refresco 600ml", 15.00, 60);
        escribir(out, 105, "Detergente 1kg", 35.00, 20);

        out.close();
        System.out.println("inventario.txt creado");
    }

    private static void escribir(PrintWriter out, int codigo, String nombre,
                                 double precio, int stock) {
        out.println(codigo + "," + nombre + "," + precio + "," + stock);
    }
}
