package es.estudium.practicatema01;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            System.out.println(info.getName() + " -> " + info.getClassName());
        }
        // - UIManager.getSystemLookAndFeelClassName() -> Nativo del SO (Windows/Mac)
        // - "javax.swing.plaf.nimbus.NimbusLookAndFeel" -> Estilo moderno de Java
        // - UIManager.getCrossPlatformLookAndFeelClassName() -> Metal (Por defecto de Java)
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (Exception e) {
            System.out.println("Error en la carga de LAF " + e.getMessage());;
        }

        Vista v = new Vista();
        Modelo m = new Modelo();

        new Controlador(v, m);
    }
}
