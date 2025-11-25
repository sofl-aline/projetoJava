package com.financeiro.persistencia;

import com.financeiro.enums.TipoTransacao;
import com.financeiro.model.Transacao;
import com.financeiro.model.Usuario;
import com.financeiro.service.SistemaFinanceiro;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JsonRepositorio {

    private static final String ARQUIVO = "dados.json";

    public static void salvar(SistemaFinanceiro sistema) {
        try {
            EstadoSistemaFinanceiro estado = new EstadoSistemaFinanceiro();

            Usuario usuario = sistema.getUsuarioLogado();
            if (usuario != null) {
                estado.setNomeUsuario(usuario.getNome());
                estado.setSenhaUsuario(usuario.getSenha());
                estado.setOrcamentoMensal(usuario.getOrcamentoMensal());
            }

            estado.setProximoId(sistema.getProximoId());

            List<TransacaoPersistencia> lista = new ArrayList<>();
            for (Transacao t : sistema.getTransacoes()) {
                TransacaoPersistencia tp = new TransacaoPersistencia(
                        t.getId(),
                        t.getDescricao(),
                        t.getValor(),
                        t.getData().toString(),
                        t.getCategoria(),
                        t.getTipo().name()
                );
                lista.add(tp);
            }
            estado.setTransacoes(lista);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try (FileWriter writer = new FileWriter(ARQUIVO)) {
                gson.toJson(estado, writer);
            }

            System.out.println("\n[INFO] Dados salvos.");
        } catch (Exception e) {
            System.out.println("[ERRO] Falha ao salvar: " + e.getMessage());
        }
    }

    public static void carregar(SistemaFinanceiro sistema) {
        try {
            if (!Files.exists(Paths.get(ARQUIVO))) return;

            Gson gson = new Gson();
            try (FileReader reader = new FileReader(ARQUIVO)) {
                EstadoSistemaFinanceiro estado =
                        gson.fromJson(reader, EstadoSistemaFinanceiro.class);

                Usuario usuario = null;
                if (estado.getNomeUsuario() != null) {
                    usuario = new Usuario(estado.getNomeUsuario(), estado.getSenhaUsuario());
                    usuario.setOrcamentoMensal(estado.getOrcamentoMensal());
                }

                ArrayList<Transacao> listaTransacoes = new ArrayList<>();
                if (estado.getTransacoes() != null) {
                    for (TransacaoPersistencia tp : estado.getTransacoes()) {
                        Transacao t = new Transacao(
                                tp.getId(),
                                tp.getCategoria(),
                                tp.getValor(),
                                LocalDate.parse(tp.getData()),
                                tp.getDescricao(),
                                TipoTransacao.valueOf(tp.getTipo())
                        );
                        listaTransacoes.add(t);
                    }
                }

                sistema.carregarEstado(usuario, listaTransacoes, estado.getProximoId());

                System.out.println("\n[INFO] Dados carregados.");
            }
        } catch (Exception e) {
            System.out.println("[ERRO] Ao carregar JSON: " + e.getMessage());
        }
    }
}
