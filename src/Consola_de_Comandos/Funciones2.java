/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Consola_de_Comandos;

import java.io.BufferedWriter;
import java.io.File;
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

    
    public void escribirArchivo (File mf, String texto) throws IOException {
        BufferedWriter write;
        if (mf.exists()) {
            if (!mf.isHidden() && mf.isFile()) {
                write = new BufferedWriter(new FileWriter(mf, true));
                write.write(texto);
                write.newLine();
                write.close();
            } else {
                throw new IOException("Error. El archivo es un directorio.");
            }
        } else {
            throw new IOException("Error. El archivo no existe");
        }
    }

   }
