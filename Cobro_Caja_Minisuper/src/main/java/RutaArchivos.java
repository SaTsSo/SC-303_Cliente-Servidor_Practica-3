package main.java;

import java.io.File;

public class RutaArchivos {

    private static final String MODULO = "Cobro_Caja_Minisuper";
    private static final String CARPETA_DATOS = "src" + File.separator + "main" + File.separator + "java";

    public static String resolver(String nombre) {
        for (String candidata : candidatas(nombre)) {
            if (new File(candidata).exists()) {
                return candidata;
            }
        }
        return rutaPorDefecto(nombre);
    }

    private static String[] candidatas(String nombre) {
        return new String[]{
                nombre,
                MODULO + File.separator + CARPETA_DATOS + File.separator + nombre,
                CARPETA_DATOS + File.separator + nombre,
                MODULO + File.separator + nombre
        };
    }

    private static String rutaPorDefecto(String nombre) {
        if (new File(MODULO, CARPETA_DATOS + File.separator + nombre).getParentFile().isDirectory()) {
            return MODULO + File.separator + CARPETA_DATOS + File.separator + nombre;
        }
        if (new File(CARPETA_DATOS).isDirectory()) {
            return CARPETA_DATOS + File.separator + nombre;
        }
        if (new File(MODULO).isDirectory()) {
            return MODULO + File.separator + nombre;
        }
        return nombre;
    }
}
