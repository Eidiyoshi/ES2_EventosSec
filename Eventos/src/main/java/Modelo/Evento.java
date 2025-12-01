/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 *
 * @author Arthur
 */
public class Evento {

    private String nome;
    private String descricao;
    private int quantidadeConvidados;
    private Localizacao localizacao;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private int duracaoEmDias;
    private Status status;
    private Buffet buffet;
    private ArrayList<Funcionario> funcionarios;
    private ArrayList<DespesaAdicional> despesasAdicionais;
    private double custoTotal;
    private double custoPorConvidado;

    public Evento(String nome, String descricao, int quantidadeConvidados, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidadeConvidados = quantidadeConvidados;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.funcionarios = new ArrayList<>();
        this.despesasAdicionais = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeConvidados() {
        return quantidadeConvidados;
    }

    public int getDuracaoEmDias() {
        return duracaoEmDias;
    }
    
    public void atualizarStatus()
    {
        if(LocalDateTime.now().isBefore(this.dataHoraInicio))
        {
            this.status = Status.AGENDAMENTO;
        }
        else if(LocalDateTime.now().isAfter(this.dataHoraFim))
        {
            this.status = Status.ENCERRADO;
        }
        else
        {
            this.status = Status.EM_ANDAMENTO;
        }
    }
    
    public void calcularDuracaoEmDias()
    {
        this.duracaoEmDias = (int)ChronoUnit.DAYS.between(this.dataHoraInicio, this.dataHoraFim);
    }
    
    public double calcularCustoTotalBuffet()
    {
        if( this.buffet == null)
        {
            return 0;
        }
        this.buffet.calcularCustoTotal();
        return this.buffet.getCustoTotal();
        
    }
    
    public void calcularCustoTotalEvento()
    {
        calcularDuracaoEmDias();
        
        double aux = 0;
        
        for(Funcionario i: funcionarios)
        {
            i.calcularPagamentoTotal(this.duracaoEmDias);
            aux += i.getPagamentoTotal();
        }
        
        for(DespesaAdicional i: despesasAdicionais)
        {
            
            aux += i.getCusto();
        }
        
        aux += localizacao.getCustoAluguelTotal();
        aux += calcularCustoTotalBuffet();
        
        this.custoTotal = aux;
    }
    
    public void calcularCustoPorConvidado()
    {
        this.custoPorConvidado = this.custoTotal / this.quantidadeConvidados;
    }
    
    public void setLocalizacao(Localizacao localizacao)
    {
        this.localizacao = localizacao;
    }
    
    public void adicionarFuncionario(Funcionario funcionario)
    {
        this.funcionarios.add(funcionario);
    }
    
    public void adicionarDespesaAdicional(DespesaAdicional despesaAdicional)
    {
        this.despesasAdicionais.add(despesaAdicional);
    }
    
    public void adicionarItemBuffet(ItemBuffet itemBuffet)
    {
        this.buffet.adicioinarItemBuffet(itemBuffet);
    }
    
    public DespesaAdicional buscarDespesaAdicional(String nomeDespesaAdicional)
    {
        for(DespesaAdicional i : despesasAdicionais)
        {
            if(i.getNome().equals(nomeDespesaAdicional))
            {
                return i;
            }
        }
        return null;
    }
    
    public Funcionario buscarFuncionario(String nomeFuncionario)
    {
        for(Funcionario i : funcionarios)
        {
            if(i.getNome().equals(nomeFuncionario))
            {
                return i;
            }
        }
        return null;
    }
    
    public ItemBuffet buscarItemBuffet(String nomeItemBuffet)
    {
        return this.buffet.buscarItemBuffet(nomeItemBuffet);
    }
    
    public void alterarStatus(Status novoStatus)
    {
        this.status = novoStatus;
    }
    
    public void removerDespesaAdicional(DespesaAdicional despesaAdicional)
    {
        this.despesasAdicionais.remove(despesaAdicional);
    }
    
    public void removerFuncionario(Funcionario funcionario)
    {
        this.funcionarios.remove(funcionario);
    }
    
    public void removerItemBuffet(ItemBuffet itemBuffet)
    {
        this.buffet.removerItemBuffet(itemBuffet);
    }
    
    public String exibirEvento()
    {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String data = dataHoraInicio.format(formatter);
      String  dataFinal = dataHoraFim.format(formatter);
            String   resultado = "<h2>Informações do Evento</h2>\n" +
       "<table>\n" +
       "  <tr><th>Nome</th><td>"+this.nome + "</td></tr>\n" +
       "  <tr><th>Descrição</th><td>"+ this.descricao + "</td></tr>\n" +
       "  <tr><th>Duração (dias)</th><td>"+ String.valueOf(duracaoEmDias) + "</td></tr>\n" +
       "  <tr><th>Quantidade de Convidados</th><td>"+ String.valueOf(quantidadeConvidados) +"</td></tr>\n" +
       "  <tr><th>Rua</th><td>" +localizacao.getRua()+"</td></tr>\n" +
       "  <tr><th>Bairro</th><td>" +localizacao.getBairro()+"</td></tr>\n" +
       "  <tr><th>Número</th><td>" +String.valueOf(localizacao.getNumero())+"</td></tr>\n" +
       "  <tr><th>Data Início</th><td>"+ data +"</td></tr></th><td><!-- data_inicio --></td></tr>\n" +


       "  <tr><th>Data Fim</th><td>"+ dataFinal +"</td></tr>\n" +
       "  <tr><th>Status</th><td>" + status.name() + "</td></tr>\n" +
                    "</table>\n" +
       "\n" +
                    "<h3>Buffet</h3>\n" +
                    "<table>\n" ;
       if(buffet != null)
               {
                   resultado += buffet.exibir() + "\n";
               }
       resultado += "</table>\n" +
       "\n" +
       "<h3>Funcionários</h3>\n" +
       "<table>\n" +
       "  <tr><th>Nome</th><th>Pagamento Total</th></tr>\n" ;
        for(Funcionario i: funcionarios)
               {
                   resultado += i.getNome()+ " ";
                   resultado += String.valueOf(i.getPagamentoTotal()) + "\n";
               }
       resultado += "</table>\n" +
       "\n" +
       "<h3>Despesas Adicionais</h3>\n" +
       "<table>\n" +
       "  <tr><th>Nome</th><th>Custo</th></tr>\n" ;
       for(DespesaAdicional i: despesasAdicionais)
               {
                   resultado += i.getNome() + " ";
                   resultado += String.valueOf(i.getCusto()) + "\n";
               }

        resultado += "</table>\n" +
       "\n" +
       "<h3>Valores Totais</h3>\n" +
       "<table>\n" +
       "  <tr><th>Custo Total</th><td>"+ String.valueOf(custoTotal) + "</td></tr>\n" +
       "  <tr><th>Custo por Convidado</th><td>" + String.valueOf(custoPorConvidado) + "</td></tr>\n" +
       "</table>";
        
        return resultado;
    }
}
