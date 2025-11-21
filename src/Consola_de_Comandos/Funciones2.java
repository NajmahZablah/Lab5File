/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Consola_de_Comandos;

import java.io.File;

/**
 *
 * @author andre
 */
public class Funciones2 extends Funciones{
   public File cambiarDirectorio(File directorioActual, String ruta) {
        if (ruta == null || ruta.isEmpty()) {

            return directorioActual;
        }
        File nuevoDir = new File(ruta);
        
        if(!nuevoDir.isAbsolute() ){
        }

    }   
}
