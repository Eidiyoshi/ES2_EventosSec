package Interface;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import Controle.Controlador;
import Modelo.CategoriaCusto;
import Modelo.Localizacao;
import Modelo.Status;
import Modelo.TipoItem;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
/**
 *
 * @author danie
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class EventSelectionDialog extends JDialog {
    private Controlador controlador;
    private JList<String> eventList;
    private DefaultListModel<String> model;
    private String selectedEvent = null;

    public EventSelectionDialog(Frame parent, Controlador controlador) {
        super(parent, "Selecionar / Criar Evento", true);
        this.controlador = controlador;
        init();
    }

    private void init() {
        setLayout(new BorderLayout(8,8));
        model = new DefaultListModel<>();
        // Supõe que controlador possua listarNomesEventos() -> List<String>
        List<String> nomes = controlador.listarNomesEventos(); // implementar se não existir
        for (String n : nomes) model.addElement(n);

        eventList = new JList<>(model);
        eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(eventList);
        add(scroll, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton criar = new JButton("Criar Evento");
        JButton selecionar = new JButton("Selecionar");
        JButton fechar = new JButton("Fechar");

        criar.addActionListener(e -> {
            EventFormDialog form = new EventFormDialog((Frame) getOwner(), controlador);
            form.setVisible(true);
            if (form.isCriado()) {
                // atualizar lista
                model.clear();
                controlador.listarNomesEventos().forEach(model::addElement);
            }
        });

        selecionar.addActionListener(e -> {
            String sel = eventList.getSelectedValue();
            if (sel == null) {
                JOptionPane.showMessageDialog(this, "Selecione um evento.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            selectedEvent = sel;
            setVisible(false);
        });

        fechar.addActionListener(e -> {
            selectedEvent = null;
            setVisible(false);
        });

        bottom.add(criar);
        bottom.add(selecionar);
        bottom.add(fechar);
        add(bottom, BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(getOwner());
    }

    public String getSelectedEvent() {
        return selectedEvent;
    }
}
