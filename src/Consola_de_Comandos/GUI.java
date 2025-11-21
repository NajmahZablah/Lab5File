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
public class GUI extends JFrame {
    
    // atributos
    private JTextArea consola;
    private Funciones1 gestor1;
    private Funciones2 gestor2;
    private String prompt;
    private File directorioActual;
    
    public GUI() {
        setTitle("Administrador - Command Prompt");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        directorioActual = new File(System.getProperty("user.dir"));
        prompt = directorioActual.getAbsolutePath() + "> ";
        
        consola = new JTextArea();
        consola.setFont(new Font("Consolas", Font.PLAIN, 14));
        consola.setBackground(Color.BLACK);
        consola.setForeground(Color.WHITE);
        consola.setCaretColor(Color.WHITE);
        
        consola.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                    
                    String textoCompleto = consola.getText();
                    int indicePrompt = textoCompleto.lastIndexOf(prompt);
                    
                    if (indicePrompt != -1) {
                        String comando = textoCompleto.substring(indicePrompt + prompt.length()).trim();
                    
                        if (!comando.isEmpty()) {
                            consola.append("\n");
                            procesarComando(comando);
                        }
                        
                        consola.append("\n" + prompt);
                        consola.setCaretPosition(consola.getText().length());
                    }
                }
                
                else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (cursorEnPrompt()) {
                        e.consume();
                    }
                }
                
                else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_UP) {
                    if (cursorEnPrompt()) {
                        e.consume();
                    }
                }
            }
            
            @Override
            public void keyTyped(KeyEvent e) {
                if (cursorEnPrompt()) {
                    consola.setCaretPosition(consola.getText().length());
                }
            }
        });
        
        consola.setText("Microsoft Windows [Version 10.0.22621.521]\n");
        consola.append("(c) Andrea, Jeremy y Najmah. Todos los derechos reservados.\n\n");
        consola.append(prompt);
        
        JScrollPane scroll = new JScrollPane(consola);
        add(scroll);
        setVisible(true);
        
        gestor1 = new Funciones1();
        gestor2 = new Funciones2();
    }
    
    private boolean cursorEnPrompt() {
        String texto = consola.getText();
        int indicePrompt = texto.lastIndexOf(prompt);
        int posicionCursor = consola.getCaretPosition();
        return posicionCursor <= indicePrompt + prompt.length();
    }
    
    private void procesarComando(String entrada) {
        try {
            
            String[] partes = entrada.trim().split("\\s+", 2);
            String comando = partes[0].toLowerCase();
            String argumento = partes.length > 1 ? partes[1] : "";
            
            switch (comando) {
                case "mkdir":
                    ejecutarMkdir(argumento);
                    break;
                case "mfile":
                    ejecutarMfile(argumento);
                    break;
                case "rm":
                    ejecutarRm(argumento);
                    break;
                case "cd":
                    ejecutarCd(argumento);
                    break;
                case "...":
                    ejecutarRegresarDir();
                    break;
                case "dir":
                    ejecutarDir();
                    break;
                case "date":
                    ejecutarDate();
                    break;
                case "time":
                    ejecutarTime();
                    break;
                case "wr":
                    ejecutarEscribir(argumento);
                    break;
                case "rd":
                    ejecutarLeer(argumento);
                    break;
                case "exit":
                    ejecutarExit();
                    break;
                default:
                    consola.append("Error: Comando no reconocido - "+comando);
                    break;
            }
        } catch (IOException e) {
            consola.append("Error de disco: "+e.getMessage());
        } catch (NullPointerException e) {
            consola.append("Error: Debe especificar un archivo o directorio");
        } catch (Exception e) {
            consola.append("Error: "+e.getMessage());
        }
    }
    
    private void ejecutarMkdir(String nombreCarpeta) throws IOException {
        if (nombreCarpeta.isEmpty()) {
            consola.append("Error: Debe especificar el nombre de la carpeta");
            return;
        }
        if (gestor1.crearCarpeta(directorioActual, nombreCarpeta)) {
            consola.append("Carpeta creada exitosamente");
        } else {
            consola.append("Error: No se pudo crear la carpeta");
        }
    }
    
    private void ejecutarMfile(String nombreArchivo) throws IOException {
        if (nombreArchivo.isEmpty()) {
            consola.append("Error: Debe especificar el nombre del archivo");
            return;
        }
        if (gestor1.crearArchivo(directorioActual, nombreArchivo)) {
            consola.append("Archivo creado exitosamente");
        } else {
            consola.append("Error: No se pudo crear el archivo");
        }
    }
    
    private void ejecutarRm(String ruta) throws IOException {
        if (ruta.isEmpty()) {
            consola.append("Error: Debe especificar la ruta a eliminar");
            return;
        }
        
        gestor1.setFile(ruta);
        File archivoEliminar = gestor1.getFile();
        
        if (archivoEliminar == null || !archivoEliminar.exists()) {
            consola.append("Error: El archivo o carpeta no existe");
            return;
        }
        
        if (gestor1.eliminar(archivoEliminar)) {
            String tipo = gestor1.esDirectorio() ? "Carpeta" : "Archivo";
            consola.append(tipo + " eliminado exitosamente");
        } else {
            consola.append("Error: No se pudo eliminar");
        }
    }
    
    private void ejecutarCd(String ruta) {
        File nuevoDir = gestor1.cambiarDirectorio(directorioActual, ruta);
        
        if (!nuevoDir.equals(directorioActual)) {
            directorioActual = nuevoDir;
            prompt = directorioActual.getAbsolutePath() + "> ";
            consola.append("Directorio cambiado");
        }
    }
    
    private void ejecutarRegresarDir(){
        File directorioAnterior = gestor1.directorioAnterior(directorioActual);
        
        if (directorioAnterior != null && !directorioAnterior.equals(directorioActual)) {
            directorioActual = directorioAnterior;
            prompt = directorioActual.getAbsolutePath() + "> ";
        } else {
            consola.append("Ya está en el directorio raíz");
        }
    }
    
    private void ejecutarDir() {
        String contenido = gestor1.listarDirectorio(directorioActual);
        consola.append(contenido);
    }
    
    private void ejecutarDate() {
        consola.append("Fecha actual: "+gestor2.getDate());
    }
    
    private void ejecutarTime() {
        consola.append("Hora actual: "+gestor2.getTime());
    }
    
    private void ejecutarEscribir(String argumentos) throws IOException {
        String[] partes = argumentos.split("\\s+", 2);
        
        if (partes.length < 2) {
            consola.append("Error: Formato incorrecto. Uso: wr <archivo> <texto>");
            return;
        }
        
        String nombreArchivo = partes[0];
        String texto = partes[1];
        
        File archivo = new File(directorioActual, nombreArchivo);
        gestor2.escribirArchivo(archivo, texto);
        consola.append("Texto guardado en el archivo");
    }
    
    private void ejecutarLeer(String nombreArchivo) throws IOException {
        if (nombreArchivo.isEmpty()) {
            consola.append("Error: Debe especificar el nombre del archivo");
            return;
        }
}
    private void ejecutarExit() {
        consola.append("Cerrando la consola...");
        System.exit(0);
    }
}
