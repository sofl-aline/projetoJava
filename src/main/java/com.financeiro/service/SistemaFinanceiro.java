package com.financeiro.service;

import com.financeiro.model.Usuario;
import com.financeiro.model.Transacao;
import com.financeiro.enums.TipoTransacao;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/*Classe principal que gerencia todas as transações financeiras.
Responsável por adicionar, listar, editar, remover transações e gerar relatórios.*/
public class SistemaFinanceiro {

    private ArrayList<Transacao> transacoes; //Lista que armazena todas as transações

    private int proximoId; //Controle do próximo Id a ser usado

    private Usuario usuarioLogado; //usuário logado no Sistema

    //Construtor
    public SistemaFinanceiro(){
        this.transacoes = new ArrayList<>();
        this.proximoId = 1;
        this.usuarioLogado = null;
    }

    // Getters
    public Usuario getUsuarioLogado() { return usuarioLogado;}


    // Setters
    public void setUsuarioLogado(Usuario usuario) { this.usuarioLogado = usuario;}

    //metodo para adicionar nova transação
    public void adicionarTransacao(double valor, String categoria, LocalDate data, String descricao, TipoTransacao tipo) {

        //Cria uma nova transacao com os dados fornecidos
        Transacao novaTransacao = new Transacao(proximoId, categoria, valor, data, descricao, tipo);
        transacoes.add(novaTransacao); //Adiciona na lista
        proximoId++; //Incrementa o Id para a próxima transação

        System.out.println("\n*Transação cadastrada com sucesso!*");

        //Verifica se precisa alertar usuário do limite do orçamento
        if (tipo == TipoTransacao.DESPESA && usuarioLogado != null && usuarioLogado.getOrcamentoMensal() > 0) {
            verificarAlertaOrcamento(); //TO DO
        }
    }

    //metodo para listar todas as transacoes
    public void listarTodasTransacoes() {
        if (transacoes.isEmpty()) {
            System.out.println("\n*Nenhuma transação cadastrada ainda!*");
            return;
        }

        System.out.println("\n***TODAS AS TRANSAÇÕES***");
        for (Transacao t : transacoes) {
            System.out.println(t);
        }
        System.out.println("***************************\n");
    }

    //Metodo para filtrar transações por mês
    public void filtrarPorMes(int mes, int ano) {
        ArrayList<Transacao> transacoesFiltradas = new ArrayList<>();

        //Percorre todas as transacoes
        for (Transacao t : transacoes) {

            //verifica se a data da transação é do mês/ano informado
            if (t.getData().getMonthValue() == mes && t.getData().getYear() == ano) {
                transacoesFiltradas.add(t);
            }
        }

        if (transacoesFiltradas.isEmpty()) {
            System.out.println("\n*Nenhuma transação encontrada para esse mês!*");
            return;
        }

        System.out.println("\n***TRANSAÇÕES DE " + mes + "/" + ano + " ***");
        for (Transacao t : transacoesFiltradas) {
            System.out.println(t);
        }
        System.out.println("***************************\n");
    }

    //TO DO
    //Metodo para filtrar transações por mês


}
