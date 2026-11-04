package es.estudium.practicatema01;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controlador implements ActionListener {
    private final Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.btnBuscar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputTxt = vista.txtField.getText().trim().toLowerCase();

        if (e.getSource() == vista.btnBuscar) {
            vista.listResultados.setText("");
            if (validarInputText(inputTxt)){
                vista.listResultados.setText("Buscando en todo el disco... Esto puede tardar unos segundos, por favor espere.");
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
                    vista.listResultados.setText("No se encontró ningún archivo con esa extensión.");
                }
                else {
                    vista.listResultados.setText("");
                    for (String fichero : ficheros){
                    vista.listResultados.append(fichero+"\n");
                }
                    }
                System.out.println(validarInputText(inputTxt));
                vista.lblBuscando.setText("Búsqueda finalizada ("+ficheros.size()+" resultados encontrados)");
            }
            else {
                vista.listResultados.setText("Introduce una extensión válida");
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
}

