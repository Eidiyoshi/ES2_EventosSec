/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Arthur
 */
public class Despesa {
    private String nome;
    private CategoriaCusto categoriaCusto;

    public Despesa(String nome, CategoriaCusto categoriaCusto) {
        this.nome = nome;
        this.categoriaCusto = categoriaCusto;
    }

    public String getNome() {
        return nome;
    }

    public CategoriaCusto getCategoriaCusto() {
        return categoriaCusto;
    }
    
    
    
    public void alterarDespesa(String nome, String categoriaCusto)
    {
        this.nome = nome;
        if(categoriaCusto.equals("CUSTO_FIXO"))
        {
            this.categoriaCusto = CategoriaCusto.CUSTO_FIXO;
        }
        else
        {
            this.categoriaCusto = CategoriaCusto.CUSTO_VARIAVEL;
        }
    }
}
