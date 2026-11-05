package es.estudium.practicatema01;

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
    private final Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.btnBuscar.addActionListener(this);
        this.vista.listResultados.addMouseListener(this);

        this.vista.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.vista.frame.getRootPane().setDefaultButton(this.vista.btnBuscar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputTxt = vista.txtField.getText().trim().toLowerCase();

        if (e.getSource() == vista.btnBuscar) {
            vista.modeloLista.clear();
            if (validarInputText(inputTxt)){
                vista.lblBuscando.setText(" ");
                vista.modeloLista.addElement("Buscando en todo el disco... Esto puede tardar unos segundos, por favor espere.");
                vista.panelMain.paintImmediately(vista.panelMain.getVisibleRect());

                if (!inputTxt.startsWith(".")) {
                    inputTxt = "." + inputTxt;
                }
                ArrayList<String> ficheros = modelo.buscarEnDiscos(inputTxt);
                if (ficheros.isEmpty()) {
                    vista.modeloLista.clear();
                    vista.modeloLista.addElement("No se encontró ningún archivo con esa extensión.");
                }
                else {
                    vista.modeloLista.clear();
                    for (String fichero : ficheros){
                        vista.modeloLista.addElement(fichero);
                    }
                }
                vista.lblBuscando.setText("Búsqueda finalizada ("+ficheros.size()+" resultados encontrados)");
                vista.lblTxt.setForeground(Color.black);
                vista.lblTxt.setFont(new Font("Segoe UI", Font.PLAIN, 12));
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
            vista.lblBuscando.setText(" ");
            vista.lblTxt.setForeground(Color.red);
            vista.lblTxt.setFont(new Font("Segoe UI", Font.BOLD,12));
            return false;
        }
        return true;
    }

    @Override
    public void mouseClicked(MouseEvent evento) {
        if (evento.getClickCount() != 2) return;
        String ruta = vista.listResultados.getSelectedValue();
        if (ruta != null && ruta.toLowerCase().endsWith(".exe")) {
            File archivo = new File(ruta);
            if (archivo.isFile() && vista.confirmarEjecucion(archivo.getName())) {
                try {
                    ProcessBuilder pb = new ProcessBuilder(archivo.getAbsolutePath());
                    Process process = pb.start();
                    long pid = process.pid();
                    System.out.println("Proceso: "+archivo.getName()+" (PID: "+pid+")");
                } catch (IOException e) {
                    vista.mostrarError("No se pudo ejecutar: " + archivo.getName());
                    System.out.println("Error: no se ejecutó el archivo " +e.getMessage());
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

