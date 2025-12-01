/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Arthur
 */
public class ItemBuffet {
    private String nome;
    private double custo;
    private TipoItem tipoitem;

    public ItemBuffet(String nome, double custo, TipoItem tipoitem) {
        this.nome = nome;
        this.custo = custo;
        this.tipoitem = tipoitem;
    }

    public String getNome() {
        return nome;
    }

    public TipoItem getTipoitem() {
        return tipoitem;
    }
    
    
    
    public double getCusto() {
        return custo;
    }
    
}
