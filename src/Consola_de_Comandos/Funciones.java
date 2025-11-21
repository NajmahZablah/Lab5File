/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Consola_de_Comandos;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author najma
 */
public class Funciones {

    // atributos
    private File archivo = null;

    public void setFile(String dirrecion) {
        archivo = new File(dirrecion);
    }

    public File getFile() {
        return archivo;
    }

    public boolean esDirectorio() {
        return archivo != null && archivo.isDirectory();
    }

    public boolean crearCarpeta(File directorio, String nombreCarpeta) throws IOException {
        File nuevaCarpeta = new File(directorio, nombreCarpeta);
        return nuevaCarpeta.mkdir();
    }

    public boolean crearArchivo(File directorio, String nombreArchivo) throws IOException {
        File nuevoArchivo = new File(directorio, nombreArchivo);
        return nuevoArchivo.createNewFile();
    }

    public boolean eliminar(File arch) throws IOException {
        if (arch.isDirectory()) {
            File[] contenido = arch.listFiles();
            if (contenido != null) {
                for (File hijo : contenido) {
                    eliminar(hijo);
                }
            }
        }
        return arch.delete();
    }

  

}
