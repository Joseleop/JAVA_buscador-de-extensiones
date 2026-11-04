package es.estudium.practicatema01;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Vista {
    JFrame frame = new JFrame("Mis ficheros");
    JPanel panelMain = new JPanel();
    JPanel panelTxtBtn = new JPanel();

    DefaultListModel<String> modeloLista = new DefaultListModel<>();
    JList<String> listResultados = new JList<>(modeloLista);
    JScrollPane scrollPane = new JScrollPane(listResultados);

    JLabel lblTxt = new JLabel("Introduce la extensión (ej: .java, .png):");
    JTextField txtField = new JTextField();
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    JButton btnBuscar = new JButton("Buscar");
    JLabel lblBuscando = new JLabel("Por favor, espere. Buscando...");

    public Vista(){

        frame.setSize(600, 700); // Tamaño mediano (600px de ancho)
        frame.setLocationRelativeTo(null);

        panelMain.setLayout(new BorderLayout(10, 10));
        panelMain.setBorder(new EmptyBorder(15, 15, 15, 15));

        listResultados.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        txtField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTxt.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblBuscando.setFont(new Font("Segoe UI", Font.BOLD, 12));

        txtField.setPreferredSize(new Dimension(0, 35));

        panelTxtBtn.setLayout(new BorderLayout(0, 15));
        panelTxtBtn.add(lblTxt);
        panelTxtBtn.add(txtField, BorderLayout.CENTER);
        btnBuscar.setPreferredSize(new Dimension(130, 35)); // Más alto (32px) y más estrecho (130px)
        panelTxtBtn.setLayout(new GridLayout(3, 1, 0, 5)); // 3 filas, espacio vertical de 8px

        panelTxtBtn.add(lblTxt);
        panelTxtBtn.add(txtField);

        // Contenedor para el botón (para controlar su tamaño y centrado)
        btnBuscar.setPreferredSize(new Dimension(130, 35)); // Ancho: 130px, Alto: 32px
        buttonPanel.add(btnBuscar);

        panelTxtBtn.add(buttonPanel);

        // Ensamblar componentes principales en la ventana
        lblBuscando.setVisible(false);
        panelMain.add(lblBuscando, BorderLayout.NORTH);
        panelMain.add(scrollPane, BorderLayout.CENTER);
        panelMain.add(panelTxtBtn, BorderLayout.SOUTH);

        frame.add(panelMain);
        frame.setVisible(true);
    }
}
