/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Arthur
 */
public class Buffet extends Despesa{
    private ArrayList<ItemBuffet> ItensBuffet; 
    private double custoTotal;
    
    public Buffet(String nome, CategoriaCusto categoriaCusto)
    {
        super(nome, categoriaCusto);
        this.ItensBuffet = new ArrayList<>();
        this.custoTotal = 0;
    }

    public double getCustoTotal() {
        return custoTotal;
    }
    
    public String exibir()
    {
        String resultado = "";
        for(ItemBuffet i: ItensBuffet)
        {
            resultado += i.getNome() + "  ";
            resultado += i.getCusto() + "\n";
        }
        return resultado;
    }
    
    public void calcularCustoTotal()
    {
        double result = 0;
        for(ItemBuffet i: ItensBuffet)
        {
            result += i.getCusto();
        }
        this.custoTotal = result;
    }
    
    public void adicioinarItemBuffet(ItemBuffet itemBuffet)
    {
        this.ItensBuffet.add(itemBuffet);
    }
    
    public ItemBuffet buscarItemBuffet(String nomeItemBuffet)
    {
        for(ItemBuffet i : ItensBuffet)
        {
            if(i.getNome().equals(nomeItemBuffet))
            {
                return i;
            }
        }
        return null;
    }
    
    public void removerItemBuffet(ItemBuffet itemBuffet)
    {
        this.ItensBuffet.remove(itemBuffet);
    }
}
