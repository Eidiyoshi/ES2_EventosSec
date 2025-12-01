package Controle;

import Catalogo.Catalogo;
import Factory.FactoryBebida;
import Factory.FactoryComida;
import Factory.FactoryDespesaAdicional;
import Factory.FactoryEvento;
import Factory.FactoryFuncionario;
import Factory.FactoryLocalizacao;
import Interface.Menu;
import Modelo.CategoriaCusto;
import Modelo.DespesaAdicional;
import Modelo.Evento;
import Modelo.Funcionario;
import Modelo.ItemBuffet;
import Modelo.Localizacao;
import Modelo.Status;
import Modelo.TipoItem;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author Sayu
 */
public class Controlador {
    
    private Catalogo catalogo;
    private String eventoSelecionado;
    
    public Controlador () {
        catalogo = new Catalogo();
    }
    
    public void removerFuncionario(String nomeEvento, String nomeFuncionario)
    {
        Evento evento = catalogo.buscarEvento(nomeEvento);
        if(evento != null)
        {
            Funcionario func = evento.buscarFuncionario(nomeFuncionario);
            if(func != null)
            {
                evento.removerFuncionario(func);
                evento.calcularCustoTotalEvento();
                evento.calcularCustoPorConvidado();
                JOptionPane.showMessageDialog(null, "Operação concluída", "Aviso", 1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Erro detectado", "Aviso", 0);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Erro detectado", "Aviso", 0);
        }
    }
    
    public void removerItemBuffet(String nomeEvento, String nomeItemBuffet)
    {
        Evento evento = catalogo.buscarEvento(nomeEvento);
        if(evento != null)
        {
            ItemBuffet item = evento.buscarItemBuffet(nomeItemBuffet);
            if(item != null)
            {
                evento.removerItemBuffet(item);
                evento.calcularCustoTotalEvento();
                evento.calcularCustoPorConvidado();
                JOptionPane.showMessageDialog(null, "Operação concluída", "Aviso", 1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Erro detectado", "Aviso", 0);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Erro detectado", "Aviso", 0);
        }
    }
    
    public void removerDespesaAdicional(String nomeEvento, String nomeDespesaAdicional)
    {
        Evento evento = catalogo.buscarEvento(nomeEvento);
        if(evento != null)
        {
            DespesaAdicional despesa = evento.buscarDespesaAdicional(nomeDespesaAdicional);
            if(despesa != null)
            {
                evento.removerDespesaAdicional(despesa);
                evento.calcularCustoTotalEvento();
                evento.calcularCustoPorConvidado();
                JOptionPane.showMessageDialog(null, "Operação concluída", "Aviso", 1);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Erro detectado", "Aviso", 0);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Erro detectado", "Aviso", 0);
        }
    }
    
    public void validarInputsDeRegistroDoEvento(String nome, String descricao, int quantidadeConvidados, String nomeLocalizacao, double latitude, double longitude, double custoAluguelPorDia, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim)
    {
        FactoryLocalizacao fLoc = new FactoryLocalizacao();
        Localizacao loc = fLoc.criarLocalizacao(nomeLocalizacao, latitude, longitude, custoAluguelPorDia);
        FactoryEvento fEve = new FactoryEvento();
        Evento eve = fEve.criarEvento(nome, descricao, quantidadeConvidados, dataHoraInicio, dataHoraFim);
        eve.calcularDuracaoEmDias();
        loc.calcularCustoAluguelTotal(custoAluguelPorDia, eve.getDuracaoEmDias());
        eve.setLocalizacao(loc);
        eve.calcularCustoTotalEvento();
        eve.calcularCustoPorConvidado();
        eve.atualizarStatus();
        catalogo.adicionarEvento(eve);
        JOptionPane.showMessageDialog(null, "Operação concluída", "Aviso", 1);
    }
    
    public void validarInputsDeRegistroDeDespesaAdicional(String nomeEvento, String nomeDespesa, CategoriaCusto categoriaCusto, String descricao, double custo)
    {
        Evento eve = catalogo.buscarEvento(nomeEvento);
        if(eve == null)
        {
            JOptionPane.showMessageDialog(null, "Erro detectado", "Aviso", 0);
        }
        else
        {
            FactoryDespesaAdicional fDes = new FactoryDespesaAdicional();
            DespesaAdicional des = fDes.criarDespesaAdicional(nomeDespesa, categoriaCusto, descricao, custo);
            des.calcularCustoTotalDespesaFixa(custo);
            des.calcularCustoTotalDespesaVariavel(custo, eve.getQuantidadeConvidados());
            eve.adicionarDespesaAdicional(des);
            eve.calcularCustoTotalEvento();
            eve.calcularCustoPorConvidado();
            JOptionPane.showMessageDialog(null, "Operação concluída", "Aviso", 1);
        }
        
    }
    
    public String exibirelatorioEventos()
    {
        return catalogo.exibirelatorioEventos();
    }
    
    public void cancelarEvento(String nomeEvento)
    {
        Evento eve = catalogo.buscarEvento(nomeEvento);
        if(eve == null)
        {
        	JOptionPane.showMessageDialog(null, "Erro detectado", "Aviso", 0);
        }
        else
        {
            catalogo.removerEvento(eve);
            JOptionPane.showMessageDialog(null, "Operação concluída", "Aviso", 1);
        }
    }
    
    public String exibirEvento(String nomeEvento)
    {
        Evento eve = catalogo.buscarEvento(nomeEvento);
        if(eve == null)
        {
        	JOptionPane.showMessageDialog(null, "Erro detectado", "Aviso", 0);
                return "";
        }
        else
        {
            return eve.exibirEvento();
        }
    }
    
    public void alterarStatus(String nomeEvento, Status novoStatus)
    {
        Evento eve = catalogo.buscarEvento(nomeEvento);
        if(eve == null)
        {
        	JOptionPane.showMessageDialog(null, "Erro detectado", "Aviso", 0);
        }
        else
        {
            eve.alterarStatus(novoStatus);
            JOptionPane.showMessageDialog(null, "Operação concluída", "Aviso", 1);
        }
    }
    
    public void validarInputsDeAlteracaoDeDespesaAdicional(String nomeEvento, String nomeDespesa, String novoNomeDespesa, CategoriaCusto novaCategoriaCusto, String novaDescricao, double novoCusto)
    {
        Evento eve = catalogo.buscarEvento(nomeEvento);
        if(eve == null)
        {
        	JOptionPane.showMessageDialog(null, "Erro detectado", "Aviso", 0);
        }
        else
        {
            DespesaAdicional des = eve.buscarDespesaAdicional(nomeDespesa);
            if(des == null)
            {
                JOptionPane.showMessageDialog(null, "Erro detectado", "Aviso", 0);
            }
            else
            {
                des.alterarDespesaAdicional(novoNomeDespesa, novaCategoriaCusto.name(), novaDescricao, novoCusto);
                eve.calcularCustoTotalEvento();
                eve.calcularCustoPorConvidado();
                JOptionPane.showMessageDialog(null, "Operação concluída", "Aviso", 1);
            }
        }
    }
    
    public void validarInputsDeRegistroDeFuncionario(String nomeEvento, String nomeFuncionario, String CPF, String funcao, double diaria)
    {
        Evento eve = catalogo.buscarEvento(nomeEvento);
        if(eve == null)
        {
            JOptionPane.showMessageDialog(null, "Erro detectado", "Aviso", 0);
        }
        else
        {
            FactoryFuncionario fFunc = new FactoryFuncionario();
            Funcionario func = fFunc.criarFuncionario(nomeFuncionario, CPF, funcao, diaria);
            func.calcularPagamentoTotal(eve.getDuracaoEmDias());
            eve.adicionarFuncionario(func);
            eve.calcularCustoTotalEvento();
            eve.calcularCustoPorConvidado();
            JOptionPane.showMessageDialog(null, "Operação concluída", "Aviso", 1);
        }
    }
    
    public void adicionarFuncionario(String nomeEvento, String nomeFuncionario, String CPF, String funcao, double diaria)
    {
        Evento eve = catalogo.buscarEvento(nomeEvento);
        if(eve == null)
        {
            JOptionPane.showMessageDialog(null, "Erro detectado", "Aviso", 0);
        }
        else
        {
            FactoryFuncionario fFunc = new FactoryFuncionario();
            Funcionario novo = fFunc.criarFuncionario(nomeFuncionario, CPF, funcao, diaria);
            novo.calcularPagamentoTotal(eve.getDuracaoEmDias());
            eve.adicionarFuncionario(novo);
            eve.calcularCustoTotalEvento();
            eve.calcularCustoPorConvidado();
            JOptionPane.showMessageDialog(null, "Operação concluída", "Aviso", 1);
        }
    }
    
    public void validarInputsDeRegistroDeItemDoBuffet(String nomeEvento, String nomeItemBuffet, TipoItem tipo, double custo)
    {
        Evento eve = catalogo.buscarEvento(nomeEvento);
        if(eve == null)
        {
            JOptionPane.showMessageDialog(null, "Erro detectado", "Aviso", 0);
        }
        else
        {
            ItemBuffet item = null;
            if(tipo == TipoItem.COMIDA)
            {
                FactoryComida fCom = new FactoryComida();
                item = fCom.criarComida(nomeItemBuffet, custo, tipo);
            }
            else if(tipo == TipoItem.BEBIDA)
            {
                FactoryBebida fCom = new FactoryBebida();
                item = fCom.criarBebida(nomeItemBuffet, custo, tipo);
            }
            eve.adicionarItemBuffet(item);
            eve.calcularCustoTotalBuffet();
            eve.calcularCustoTotalEvento();
            eve.calcularCustoPorConvidado();
            JOptionPane.showMessageDialog(null, "Operação concluída", "Aviso", 1);
        }
    }
    public List<String> listarNomesEventos() {
        return catalogo.listarNomesEventos();
    }
   

}
