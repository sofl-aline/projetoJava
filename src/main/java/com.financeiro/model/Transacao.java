package com.financeiro.model;

/* importação das bibliotecas necessárias (externas e das pastas que ficam separadas)
caso não faça dá erro*/

import com.financeiro.enums.TipoTransacao;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/* Classe que representa uma transação financeira (Receita ou despesa) e
armazena informações como valor, categoria, data, descrição e tipo */

public class Transacao {

    // Atributos da classe
    private int id;
    private String descricao;
    private double valor;
    private LocalDate data;
    private String categoria;
    private TipoTransacao tipo; // RECEITA ou DESPESA

    // Construtor da classe
    public Transacao(int id, String descricao, double valor, LocalDate data, String categoria, TipoTransacao tipo) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
        this.tipo = tipo;
    }

    // Getters
    public int getId() { return id;}

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public String getCategoria() {
        return categoria;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    // Setters

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }


    public void setData(LocalDate data) {
        this.data = data;
    }


    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    // Metodo para exibir a transação de forma formatada
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String tipoStr = (tipo == TipoTransacao.RECEITA) ? "RECEITA" : "DESPESA";
        return String.format("ID: %d | %s | R$ %.2f | Categoria: %s | Data: %s | Descrição: %s",
                id, tipoStr, valor, categoria, data.format(formatter), descricao);
    }

}
