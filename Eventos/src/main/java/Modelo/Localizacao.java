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
    private double latitude;
    private double longitude;
    private double custoAluguelPorDia;
    private double custoAluguelTotal;

    public Localizacao(String nome, double latitude, double longitude, double custoAluguelPorDia) {
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.custoAluguelPorDia = custoAluguelPorDia;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
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
