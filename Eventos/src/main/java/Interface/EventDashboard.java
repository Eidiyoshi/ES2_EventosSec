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
import javax.swing.JOptionPane;
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





public class EventDashboard extends JFrame {
    private Controlador controlador;
    private String evento;

    public EventDashboard(Controlador controlador, String evento) {
        super("Painel do Evento - " + evento);
        this.controlador = controlador;
        this.evento = evento;
        init();
    }

    private void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel main = new JPanel(new GridLayout(0,2,8,8));
        JButton registrarDespesa = new JButton("Registrar Despesa Adicional");
        JButton inserirItem = new JButton("Inserir Item Buffet");
        JButton inserirFuncionario = new JButton("Inserir Funcionário");
        JButton alterarDespesas = new JButton("Alterar Despesas Adicionais");
        JButton removerItem = new JButton("Remover Item Buffet");
        JButton removerFuncionario = new JButton("Remover Funcionário");
        JButton relatorio = new JButton("Ver Relatório deste Evento");
        JButton voltar = new JButton("Voltar");

        registrarDespesa.addActionListener(e -> {
            // abrir diálogo reutilizando o método de registrarDespesaAdicional, mas com evento preenchido
            // aqui vamos abrir um diálogo simples que pede somente os campos restantes
            String nomeDespesa = JOptionPane.showInputDialog(this, "Nome da Despesa Adicional:");
            if (nomeDespesa == null) return;
            String[] options = {"CUSTO_FIXO","CUSTO_VARIAVEL"};
            int cat = JOptionPane.showOptionDialog(this, "Categoria:", "Categoria", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            String descricao = JOptionPane.showInputDialog(this, "Descrição:");
            String custo = JOptionPane.showInputDialog(this, "Custo por pessoa:");
            if (nomeDespesa != null && descricao != null && custo != null && cat >= 0) {
                try {
                    if (options[cat].equals("CUSTO_FIXO")) {
                        controlador.validarInputsDeRegistroDeDespesaAdicional(evento, nomeDespesa, Modelo.CategoriaCusto.CUSTO_FIXO, descricao, Double.parseDouble(custo));
                    } else {
                        controlador.validarInputsDeRegistroDeDespesaAdicional(evento, nomeDespesa, Modelo.CategoriaCusto.CUSTO_VARIAVEL, descricao, Double.parseDouble(custo));
                    }
                    JOptionPane.showMessageDialog(this, "Despesa registrada.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro : " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        inserirItem.addActionListener(e -> {
            // pedir nome e custo e tipo
            String nome = JOptionPane.showInputDialog(this, "Nome do item do buffet:");
            String[] tipos = {"BEBIDA","COMIDA"};
            int t = JOptionPane.showOptionDialog(this, "Tipo:", "Tipo", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tipos, tipos[0]);
            String custo = JOptionPane.showInputDialog(this, "Custo:");
            if (nome != null && custo != null && t >= 0) {
                try {
                    Modelo.TipoItem tipo = tipos[t].equals("BEBIDA") ? Modelo.TipoItem.BEBIDA : Modelo.TipoItem.COMIDA;
                    controlador.validarInputsDeRegistroDeItemDoBuffet(evento, nome, tipo, Double.parseDouble(custo));
                    JOptionPane.showMessageDialog(this, "Item inserido.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        inserirFuncionario.addActionListener(e -> {
            try {
                String nome = JOptionPane.showInputDialog(this, "Nome do funcionário:");
                String cpf = JOptionPane.showInputDialog(this, "CPF:");
                String funcao = JOptionPane.showInputDialog(this, "Função:");
                String diaria = JOptionPane.showInputDialog(this, "Diária:");
                if (nome != null && cpf != null && funcao != null && diaria != null) {
                    controlador.adicionarFuncionario(evento, nome, cpf, funcao, Double.parseDouble(diaria));
                    JOptionPane.showMessageDialog(this, "Funcionário adicionado.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        relatorio.addActionListener(e -> {
            String texto = controlador.exibirEvento(evento); // reaproveita método existente
            JTextArea area = new JTextArea(texto, 20, 50);
            area.setLineWrap(true);
            area.setWrapStyleWord(true);
            area.setEditable(false);
            JScrollPane scroll = new JScrollPane(area);
            JOptionPane.showMessageDialog(this, scroll, "Relatório - " + evento, JOptionPane.PLAIN_MESSAGE);
        });

        // Ações de remover/alterar podem chamar os métodos existentes do controlador similarmente
        alterarDespesas.addActionListener(e -> {
            // abrir diálogo de alteração simplificado, ou redirecionar ao método existente
            JOptionPane.showMessageDialog(this, "Use a função Alterar Despesas (implementação similar).");
        });

        removerItem.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog(this, "Nome do item do buffet a remover:");
            if (nome != null) {
                controlador.removerItemBuffet(evento, nome);
                JOptionPane.showMessageDialog(this, "Solicitação de remoção enviada.");
            }
        });

        removerFuncionario.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog(this, "Nome do funcionário a remover:");
            if (nome != null) {
                controlador.removerFuncionario(evento, nome);
                JOptionPane.showMessageDialog(this, "Solicitação de remoção enviada.");
            }
        });

        voltar.addActionListener(e -> dispose());

        main.add(registrarDespesa);
        main.add(inserirItem);
        main.add(inserirFuncionario);
        main.add(alterarDespesas);
        main.add(removerItem);
        main.add(removerFuncionario);
        main.add(relatorio);
        main.add(voltar);

        add(main, BorderLayout.CENTER);
    }
}

