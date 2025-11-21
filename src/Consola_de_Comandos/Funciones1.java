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
public class Funciones1 {
    
    // atributo
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
    
    public File cambiarDirectorio(File directorioActual, String ruta) {
      
   if (ruta == null || ruta.isEmpty()) {
            return directorioActual;
        }

        File nuevoDir = new File(ruta);
        
        if (!nuevoDir.isAbsolute()) {
            nuevoDir = new File(directorioActual, ruta);
        }

        if (nuevoDir.exists() && nuevoDir.isDirectory()) {
            return nuevoDir;
        } else {
            System.out.println("Error: La ruta no existe o no es un directorio");
            return directorioActual;
        }
    }   

    public String listaDirectorio(File directorio){
    if(!directorio.exists()){
    return "Error: El directorio no existe.";
    
    }
    
    if(!directorio.isDirectory()){
    return "Error:La ruta especificada no es un directorio";
    }
    
    StringBuilder resultado = new StringBuilder();
    
    File[] contenido = directorio.listFiles();
    if (contenido== null  || contenido.length ==0){
    return "El directorio esta vacio";
    }
      
    for(File elemento: contenido){
    if(!elemento.isHidden()){
    String tipo = elemento.isDirectory()?"<DIR>":"  ";
    resultado.append("\n").append(tipo).append(" - ").append(elemento.getName());
    }
    }
        return resultado.toString();

    }
}
