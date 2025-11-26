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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JsonRepositorio {

    public static void salvar(SistemaFinanceiro sistema) {
        try {
            EstadoSistemaFinanceiro estado = new EstadoSistemaFinanceiro();

            Usuario usuario = sistema.getUsuarioLogado();
            if (usuario != null) {
                estado.setNomeUsuario(usuario.getNome());
                estado.setSenhaUsuario(usuario.getSenha());
                estado.setOrcamentoMensal(usuario.getOrcamentoMensal());
            }

            String ARQUIVO = getArquivoUsuario(usuario.getNome());

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

    public static Usuario carregarParaUsuario(SistemaFinanceiro sistema, String nomeUsuario) {
        String arquivo = getArquivoUsuario(nomeUsuario);

        Path path = Paths.get(arquivo);
        if (!Files.exists(path)) {
            // nunca logou antes → não tem arquivo
            System.out.println("[INFO] Nenhum arquivo encontrado para o usuário " + nomeUsuario);
            return null;
        }

        try (FileReader reader = new FileReader(arquivo)) {
            Gson gson = new Gson();
            EstadoSistemaFinanceiro estado =
                    gson.fromJson(reader, EstadoSistemaFinanceiro.class);

            if (estado == null) {
                return null;
            }

            // Reconstrói o usuário a partir do JSON
            Usuario usuario = new Usuario(
                    estado.getNomeUsuario(),
                    estado.getSenhaUsuario()
            );
            usuario.setOrcamentoMensal(estado.getOrcamentoMensal());

            // Reconstrói as transações
            ArrayList<Transacao> transacoes = new ArrayList<>();
            if (estado.getTransacoes() != null) {
                for (TransacaoPersistencia tp : estado.getTransacoes()) {
                    LocalDate data = LocalDate.parse(tp.getData()); // yyyy-MM-dd
                    TipoTransacao tipo = TipoTransacao.valueOf(tp.getTipo());

                    Transacao t = new Transacao(
                            tp.getId(),
                            tp.getCategoria(),
                            tp.getValor(),
                            data,
                            tp.getDescricao(),
                            tipo
                    );
                    transacoes.add(t);
                }
            }

            // Joga tudo para dentro do sistema
            sistema.carregarEstado(usuario, transacoes, estado.getProximoId());

            System.out.println("[INFO] Dados carregados do arquivo " + arquivo);
            return usuario;

        } catch (IOException e) {
            System.out.println("[ERRO] Erro ao carregar dados: " + e.getMessage());
            return null;
        }
    }


    public static String getArquivoUsuario(String nomeUsuario) {
        String nomeSanitizado = nomeUsuario
                .toLowerCase()
                .trim()
                .replaceAll("\\s+", "_"); // troca espaços por underline

        return "dados_" + nomeSanitizado + ".json";
    }

}
