/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Arthur
 */
public class DespesaAdicional extends Despesa{
    private String descricao;
    private double custo;

    public DespesaAdicional(String nome, CategoriaCusto categoriaCusto, String descricao, double custo) {
        super(nome, categoriaCusto);
        this.descricao = descricao;
        this.custo = custo;
    }

    public double getCusto() {
        return this.custo;
    }

    public String getDescricao() {
        return descricao;
    }
    
    
    
    public void calcularCustoTotalDespesaFixa(double custo)
    {
        this.custo += custo;
    }
    
    public void calcularCustoTotalDespesaVariavel(double custoPorConvidado, int quantidadeConvidados)
    {
        this.custo += custoPorConvidado * quantidadeConvidados;
    }
    
    public void alterarDespesaAdicional(String novoNome, String novaCategoriaCusto, String novaDescricao, double novoCusto)
    {
        super.alterarDespesa(novoNome, novaCategoriaCusto);
        this.descricao = novaDescricao;
        this.custo = novoCusto;
    }
}
