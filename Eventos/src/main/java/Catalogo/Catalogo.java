/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Catalogo;

import Modelo.Evento;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Arthur
 */
public class Catalogo {

    private ArrayList<Evento> eventos;

    public Catalogo() {
        this.eventos = new ArrayList<>();
    }
    
    public void adicionarEvento(Evento evento)
    {
        this.eventos.add(evento);
    }
    
    public Evento buscarEvento(String nomeEvento)
    {
        for(Evento i: eventos)
        {
            if(i.getNome().equals(nomeEvento))
            {
                return i;
            }
        }
        return null;
    }
    
    public String exibirelatorioEventos()
    {
        String resultado = "<!DOCTYPE html>\n" +
"<html lang=\"pt-BR\">\n" +
"<head>\n" +
"<meta charset=\"UTF-8\">\n" +
"<title>Resumo do Evento</title>\n" +
"<style>\n" +
"  body { font-family: Arial, sans-serif; margin: 20px; }\n" +
"  table { border-collapse: collapse; width: 100%; }\n" +
"  th, td { border: 1px solid #444; padding: 8px; }\n" +
"  th { background-color: #ddd; }\n" +
"</style>\n" +
"</head>\n" +
"<body>";
        for(Evento i: eventos)
        {
            resultado += i.exibirEvento();
        }
        resultado += "</body>\n" +
"</html>";
        return resultado;
    }
    
    public void removerEvento(Evento evento)
    {
        this.eventos.remove(evento);
    }
    public List<String> listarNomesEventos() {
    List<String> nomes = new ArrayList<>();
    for (Evento e : eventos) {
        nomes.add(e.getNome());
    }
    Collections.sort(nomes);
    return nomes;
}

}
