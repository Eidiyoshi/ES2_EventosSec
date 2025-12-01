/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Arthur
 */
public class Localizacao {
    private String nome;
    private String rua;
    private String bairro;
    private String numero;
    private double custoAluguelPorDia;
    private double custoAluguelTotal;

    public Localizacao(String nome, String rua, String bairro, String numero, double custoAluguelPorDia) {
        this.nome = nome;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.custoAluguelPorDia = custoAluguelPorDia;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public void calcularCustoAluguelTotal(double custoAluguelPorDia, int duracaoEmDias)
    {
        this.custoAluguelTotal = custoAluguelPorDia * duracaoEmDias;
    }

    public double getCustoAluguelPorDia() {
        return custoAluguelPorDia;
    }

    public double getCustoAluguelTotal() {
        return custoAluguelTotal;
    }

    public void setCustoAluguelPorDia(double custoAluguelPorDia) {
        this.custoAluguelPorDia = custoAluguelPorDia;
    }

    public void setCustoAluguelTotal(double custoAluguelTotal) {
        this.custoAluguelTotal = custoAluguelTotal;
    }
    
    
    
}
