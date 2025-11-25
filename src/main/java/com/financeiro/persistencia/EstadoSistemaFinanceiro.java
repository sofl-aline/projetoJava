package com.financeiro.persistencia;

import java.util.List;

public class EstadoSistemaFinanceiro {

    private String nomeUsuario;
    private String senhaUsuario;
    private double orcamentoMensal;
    private int proximoId;
    private List<TransacaoPersistencia> transacoes;

    // Getters e setters

    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public String getSenhaUsuario() { return senhaUsuario; }
    public void setSenhaUsuario(String senhaUsuario) { this.senhaUsuario = senhaUsuario; }

    public double getOrcamentoMensal() { return orcamentoMensal; }
    public void setOrcamentoMensal(double orcamentoMensal) { this.orcamentoMensal = orcamentoMensal; }

    public int getProximoId() { return proximoId; }
    public void setProximoId(int proximoId) { this.proximoId = proximoId; }

    public List<TransacaoPersistencia> getTransacoes() { return transacoes; }
    public void setTransacoes(List<TransacaoPersistencia> transacoes) { this.transacoes = transacoes; }
}
