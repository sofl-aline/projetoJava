package com.financeiro.persistencia;

import com.financeiro.model.Transacao;
import com.financeiro.model.Usuario;
import com.financeiro.service.SistemaFinanceiro;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.financeiro.persistencia.JsonRepositorio.getArquivoUsuario;

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

    public static void salvar(SistemaFinanceiro sistema) {
        Usuario usuario = sistema.getUsuarioLogado();

        if (usuario == null) {
            System.out.println("[INFO] Nenhum usuário logado. Nada para salvar.");
            return;
        }

        String arquivo = getArquivoUsuario(usuario.getNome());

        EstadoSistemaFinanceiro estado = new EstadoSistemaFinanceiro();
        estado.setNomeUsuario(usuario.getNome());
        estado.setSenhaUsuario(usuario.getSenha());
        estado.setOrcamentoMensal(usuario.getOrcamentoMensal());
        estado.setProximoId(sistema.getProximoId());

        // Monta a lista de TransacaoPersistencia
        ArrayList<TransacaoPersistencia> lista = new ArrayList<>();
        for (Transacao t : sistema.getTransacoes()) {
            TransacaoPersistencia tp = new TransacaoPersistencia(
                    t.getId(),
                    t.getDescricao(),
                    t.getValor(),
                    t.getData().toString(),    // LocalDate → String (yyyy-MM-dd)
                    t.getCategoria(),
                    t.getTipo().name()         // enum → String
            );
            lista.add(tp);
        }
        estado.setTransacoes(lista);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(estado, writer);
            System.out.println("[INFO] Dados salvos em " + arquivo);
        } catch (IOException e) {
            System.out.println("[ERRO] Erro ao salvar dados: " + e.getMessage());
        }
    }

}
