/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Consola_de_Comandos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author najma
 */
public class GUI {
    
    // atributos
    private JTextArea consola;
    private Funciones gestor;
    private String prompt;
    private File directorioActual;
    
    public GUI() {
        setTitle("Administrador - Command Prompt");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        directorioActual = new File(System.getProperty("user.dir"));
        prompt = directorioActual.getAbsolutePath() + "> ";
    }
}
