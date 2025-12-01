/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

/**
 *
 * @author danie
 */


import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Controle.Controlador;
import Modelo.CategoriaCusto;
import Modelo.Localizacao;
import Modelo.Status;
import Modelo.TipoItem;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class EventFormDialog extends JDialog {
    private Controlador controlador;
    private boolean criado = false;

    public EventFormDialog(Frame parent, Controlador controlador) {
        super(parent, "Criar Evento", true);
        this.controlador = controlador;
        init();
    }

    private void init() {
        JPanel panel = new JPanel(new GridLayout(0,2,5,5));
        JTextField nomeField = new JTextField(15);
        JTextField descricaoField = new JTextField(15);
        JTextField quantidadeField = new JTextField(5);
        JTextField localField = new JTextField(10);
        JTextField longitudeField = new JTextField(10);
        JTextField latitudeField = new JTextField(10);
        JTextField aluguelDiarioField = new JTextField(10);
        JTextField inicioField = new JTextField(15);
        JTextField fimField = new JTextField(15);

        panel.add(new JLabel("Nome:")); panel.add(nomeField);
        panel.add(new JLabel("Descrição:")); panel.add(descricaoField);
        panel.add(new JLabel("Quantidade:")); panel.add(quantidadeField);
        panel.add(new JLabel("Nome do local:")); panel.add(localField);
        panel.add(new JLabel("Longitude:")); panel.add(longitudeField);
        panel.add(new JLabel("Latitude:")); panel.add(latitudeField);
        panel.add(new JLabel("Aluguel Diario:")); panel.add(aluguelDiarioField);
        panel.add(new JLabel("Data inicio (yyyy-MM-dd HH:mm:ss):")); panel.add(inicioField);
        panel.add(new JLabel("Data fim (yyyy-MM-dd HH:mm:ss):")); panel.add(fimField);

        JButton ok = new JButton("Criar");
        JButton cancelar = new JButton("Cancelar");
        JPanel bottom = new JPanel();
        bottom.add(ok); bottom.add(cancelar);

        ok.addActionListener(e -> {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dataHoraInicio = LocalDateTime.parse(inicioField.getText(), formatter);
                LocalDateTime dataHoraFim = LocalDateTime.parse(fimField.getText(), formatter);

                controlador.validarInputsDeRegistroDoEvento(
                    nomeField.getText(),
                    descricaoField.getText(),
                    Integer.parseInt(quantidadeField.getText()),
                    localField.getText(),
                    Float.parseFloat(latitudeField.getText()),
                    Float.parseFloat(longitudeField.getText()),
                    Float.parseFloat(aluguelDiarioField.getText()),
                    dataHoraInicio,
                    dataHoraFim
                );
                criado = true;
                JOptionPane.showMessageDialog(this, "Evento criado com sucesso.", "OK", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelar.addActionListener(e -> setVisible(false));

        getContentPane().setLayout(new BorderLayout(5,5));
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bottom, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(getOwner());
    }

    public boolean isCriado() { return criado; }
}
