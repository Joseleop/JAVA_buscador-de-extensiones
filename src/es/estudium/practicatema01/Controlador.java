package es.estudium.practicatema01;

import javax.management.Descriptor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controlador implements ActionListener, MouseListener {
    private final Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.listResultados.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputTxt = vista.txtField.getText().trim().toLowerCase();

        if (e.getSource() == vista.btnBuscar) {
            vista.modeloLista.clear();
            vista.modeloLista.addElement("C:\\Windows\\System32\\notepad.exe");
            vista.modeloLista.addElement("C:\\Windows\\System32\\mspaint.exe");
            vista.modeloLista.addElement("C:\\Users\\LeoRuiz\\Downloads\\Tema 1 - Programación Multiproceso - Práctica.pdf");
            vista.modeloLista.addElement("C:\\Users\\LeoRuiz\\Downloads\\index(1).html");

            if (validarInputText(inputTxt)){
                vista.modeloLista.addElement("Buscando en todo el disco... Esto puede tardar unos segundos, por favor espere.");
                vista.lblBuscando.setVisible(true);
                vista.lblBuscando.setText("Estado: Buscando...");

                // Con esta línea se fuerza al programa a pintar lo que hay antes de seguir.
                // Tengo que mirar si es buena praxis, es inseguro o podría haber otro modo
                vista.listResultados.paintImmediately(vista.listResultados.getVisibleRect());

                if (!inputTxt.startsWith(".")) {
                    inputTxt = "." + inputTxt;
                }
                ArrayList<String> ficheros = modelo.buscarEnDiscos(inputTxt);
                if (ficheros.isEmpty()) {
                    vista.modeloLista.addElement("No se encontró ningún archivo con esa extensión.");
                }
                else {
                    vista.modeloLista.clear();
                    for (String fichero : ficheros){
                        vista.modeloLista.addElement(fichero);
                }
                    }
                System.out.println(validarInputText(inputTxt));
                vista.lblBuscando.setText("Búsqueda finalizada ("+ficheros.size()+" resultados encontrados)");
            }
            else {
                vista.modeloLista.addElement("Introduce una extensión válida");
            }

            vista.txtField.setText("");
            vista.txtField.requestFocus();
        }
    }
    private boolean validarInputText(String texto) {
        String patron = "^\\.?[a-zA-Z0-9]+$";

        if (texto.isBlank() || !texto.matches(patron)) {
            return false;
        }
        return true;
    }

    @Override
    public void mouseClicked(MouseEvent evento) {
        if(evento.getClickCount() == 2) {
            String ruta = vista.listResultados.getSelectedValue();
            File archivo = new File(ruta);
            Desktop desktop = Desktop.getDesktop();
            if (ruta.endsWith(".exe")) {
                ProcessBuilder pb = new ProcessBuilder(ruta);
                //TODO: ejecutar los archivos .exe
                if (archivo.exists()) {
                    try {
                        pb.start();
                    } catch (IOException e) {
                        System.out.println("Error a la hora de abrir archivo." + e.getMessage());
                        //TODO intento meter un pequeño dialog para que avise del error?
                    }
                }
            } else {
                try {
                    desktop.open(archivo);
                } catch (IOException e) {
                    System.out.println("Error a la hora de abrir archivo." + e.getMessage());
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

