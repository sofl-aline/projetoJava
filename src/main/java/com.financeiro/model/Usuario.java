package com.financeiro.model;

import java.util.ArrayList;
import java.util.List;

/*Classe que representa um usuário do sistema.
Contem informações básicas como nome, senha e orçamento mensal*/

public class Usuario {

    //Atributos da Classe
    private String nome;
    private String senha;
    private double orcamentoMensal;
    private List<Transacao> transacoes = new ArrayList<>();

    //Construtor da Classe
    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.orcamentoMensal = 0.0; // Por padrão, não possui orçamento definido
    }

    // Métodos para adicionar e consultar transações
    //TO DO
    public void adicionarTransacao(Transacao t) {
        transacoes.add(t);
    }


    // Getters
    public String getNome() { return nome; }

    public String getSenha() { return senha;}

    public double getOrcamentoMensal() { return orcamentoMensal;}


    // Setters
    public void setNome(String nome) { this.nome = nome; }

    public void setSenha(String senha) { this.senha = senha;}

    public void setOrcamentoMensal(double orcamentoMensal) {this.orcamentoMensal = orcamentoMensal;}


    //TO DO
    //metodo para verificar se senha esta correta
    public boolean validarSenha(String senhaDigitada) { return this.senha.equals(senhaDigitada);}
}
