package model;

import java.time.LocalDate;

public class Transacao {
    // Atributos
    private String descricao;
    private double valor;
    private LocalDate data;
    private String categoria;
    private TipoTransacao tipo; // RECEITA ou DESPESA

    // Construtor
    public Transacao(String descricao, double valor, LocalDate data, String categoria, TipoTransacao tipo) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
        this.tipo = tipo;
    }

    // Getters e Setters
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }


}
