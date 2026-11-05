package es.estudium.practicatema01;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Vista {
    JFrame frame = new JFrame("Mis ficheros");
    JPanel panelMain = new JPanel();
    JPanel panelTxtBtn = new JPanel();
    JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));

    DefaultListModel<String> modeloLista = new DefaultListModel<>();
    JList<String> listResultados = new JList<>(modeloLista);
    JScrollPane scrollPane = new JScrollPane(listResultados);

    JLabel lblBuscando = new JLabel(" ");
    JLabel lblTxt = new JLabel("Introduce la extensión (ej: .java, .png):");
    JTextField txtField = new JTextField();
    JButton btnBuscar = new JButton("Buscar");

    public Vista(){

        frame.setSize(600, 700);
        frame.setLocationRelativeTo(null);

        listResultados.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTxt.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblBuscando.setFont(new Font("Segoe UI", Font.BOLD, 11));

        txtField.setPreferredSize(new Dimension(0, 35));
        btnBuscar.setPreferredSize(new Dimension(130, 35));
        lblBuscando.setPreferredSize(new Dimension(0, 15));

        panelTxtBtn.setBorder(new EmptyBorder(5, 0, 0, 0));
        panelTxtBtn.setLayout(new GridLayout(3, 1, 0, 5));
        panelMain.setLayout(new BorderLayout(10, 5));
        panelMain.setBorder(new EmptyBorder(8, 15, 15, 15));

        panelButton.add(btnBuscar);
        panelTxtBtn.add(lblTxt);
        panelTxtBtn.add(txtField);
        panelTxtBtn.add(panelButton);

        panelMain.add(lblBuscando, BorderLayout.NORTH);
        panelMain.add(scrollPane, BorderLayout.CENTER);
        panelMain.add(panelTxtBtn, BorderLayout.SOUTH);

        frame.add(panelMain);

        frame.setVisible(true);

        txtField.requestFocusInWindow();
    }

    public boolean confirmarEjecucion(String nombre){
        int respuesta = JOptionPane.showConfirmDialog(frame, "El archivo "+nombre+" es un ejecutable. ¿Quieres iniciarlo?","Confirmar ejecución", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        return respuesta == JOptionPane.YES_OPTION;
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
