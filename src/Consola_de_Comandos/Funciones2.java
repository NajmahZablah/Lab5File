/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Consola_de_Comandos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jerem
 */
public class Funciones2 extends Funciones1{
    
    public String getDate() {
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        
        return fecha.format(Calendar.getInstance().getTime());
    }
    
    public String getTime() {
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        return time.format(new Date());
    }

    public void escribirArchivo(File mf, String texto) throws IOException {
        if (!mf.exists()) {
            throw new IOException("Error. El archivo no existe");
        }
        
        if (mf.isDirectory()) {
            throw new IOException("Error. El archivo es un directorio");
        }
        
        if (mf.isHidden()) {
            throw new IOException("Error. El archivo está oculto");
        }
        
        if (!esArchivoTexto(mf)) {
            throw new IOException("Solo se puede escribir en archivos de texto (.txt, .log, .csv, etc.)");
        }
        
        BufferedWriter write = new BufferedWriter(new FileWriter(mf, true));
        write.write(texto);
        write.newLine();
        write.close();
    }
    
    
    
    private boolean esArchivoTexto(File archivo) {
            String nombre = archivo.getName().toLowerCase();

            String[] extensionesPermitidas = {
                ".txt", ".log", ".csv", ".xml", ".json", 
                ".html", ".css", ".js", ".java", ".py",
                ".c", ".cpp", ".h", ".md", ".ini", ".cfg"
            };

            for (String extension : extensionesPermitidas) {
                if (nombre.endsWith(extension)) {
                    return true;
                }
            }
            return false;
    }
    
    public String leerArchivo(File archivo) throws IOException {
        if (!archivo.exists()) {
            throw new IOException("Error: El archivo no existe");
        }
        
        if (archivo.isDirectory()) {
            throw new IOException("Error: La ruta es un directorio, no un archivo");
        }
        
        if (!esArchivoTexto(archivo)) {
            throw new IOException("Solo se puede leer archivos de texto (.txt, .log, .csv, etc.)");
        }
        
        StringBuilder contenido = new StringBuilder();
        BufferedReader lector = new BufferedReader(new FileReader(archivo));
        String linea;
        
        while ((linea = lector.readLine()) != null) {
            contenido.append(linea).append("\n");
        }
        
        lector.close();
        return contenido.toString();
    }
}