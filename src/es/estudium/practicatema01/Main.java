package es.estudium.practicatema01;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        // - UIManager.getSystemLookAndFeelClassName() -> Nativo del SO (Windows/Mac)
        // - "javax.swing.plaf.nimbus.NimbusLookAndFeel" -> Estilo moderno de Java
        // - UIManager.getCrossPlatformLookAndFeelClassName() -> Metal (Por defecto de Java)
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Error en la carga de LAF " + e.getMessage());
        }

        Vista v = new Vista();
        Modelo m = new Modelo();

        new Controlador(v, m);
    }
}
