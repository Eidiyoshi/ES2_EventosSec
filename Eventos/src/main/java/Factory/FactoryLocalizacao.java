/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Factory;

import Modelo.Localizacao;

/**
 *
 * @author Arthur
 */
public class FactoryLocalizacao {
    public Localizacao criarLocalizacao(String nome, String rua, String bairro, String numero, double custoAluguelPorDia)
    {
        if(!nome.equals("") && !rua.equals("") ){
            return new Localizacao(nome, rua, bairro, numero, custoAluguelPorDia);
        }
        
        return null;
        
    }
}
