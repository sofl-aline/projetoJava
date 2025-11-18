package com.financeiro.service;

import com.financeiro.model.Usuario;
import com.financeiro.model.Transacao;
import com.financeiro.enums.TipoTransacao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*Classe principal que gerencia todas as transações financeiras.
Responsável por adicionar, listar, editar, remover transações e gerar relatórios.*/

public class SistemaFinanceiro {

    private final ArrayList<Transacao> transacoes; //Lista que armazena todas as transações

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

    //METODOS
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


    //Sobrecarga do metodo filtrarPorMes
    public void filtrarPorMes(int mesInicial, int mesFinal, int ano) {
        if (mesInicial > mesFinal) {
            int temp = mesInicial;
            mesInicial = mesFinal;
            mesFinal = temp;
        }

        ArrayList<Transacao> transacoesFiltradas = new ArrayList<>();

        for (Transacao t : transacoes) {
            LocalDate data = t.getData();
            if (data.getYear() == ano) {
                int mes = data.getMonthValue();
                if (mes >= mesInicial && mes <= mesFinal) {
                    transacoesFiltradas.add(t);
                }
            }
        }

        if (transacoesFiltradas.isEmpty()) {
            System.out.println("\n*Nenhuma transação encontrada para este período!*");
            return;
        }

        System.out.println("\n*** TRANSAÇÕES DE "
                + mesInicial + " A " + mesFinal + "/" + ano + " ***");
        for (Transacao t : transacoesFiltradas) {
            System.out.println(t);
        }
        System.out.println("***************************\n");
    }


    //Metodo para filtrar por categoria
    public void filtrarPorCategoria(String categoria) {
        ArrayList<Transacao> transacoesFiltradas = new ArrayList<>();

        for(Transacao t: transacoes) {
            if(t.getCategoria().equalsIgnoreCase(categoria)) {
                transacoesFiltradas.add(t);
            }

        }

        if (transacoesFiltradas.isEmpty()) {
            System.out.println("\n*Nenhuma transação encontrada para esta categoria!*");
            return;
        }

        System.out.println("\n*** TRANSAÇÕES: " + categoria.toUpperCase() + " ***");
        for (Transacao t: transacoesFiltradas) {
            System.out.println(t);
        }
        System.out.println("***************************\n");
    }

    //Metodo para buscar uma transação por ID
    public Transacao buscarPorId(int id) {
        for (Transacao t : transacoes) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null; //Não encontrou
    }

    public void editarTransacao(int id, double novoValor, String novaCategoria, LocalDate novaData, String novaDescricao) {
        Transacao transacao = buscarPorId(id);

        if (transacao == null) {
            System.out.println("\n*Transação não encontrada!*");
            return;
        }

        //Atualiza dados da transicao
        transacao.setValor(novoValor);
        transacao.setCategoria(novaCategoria);
        transacao.setData(novaData);
        transacao.setDescricao(novaDescricao);

        System.out.println("\n*Transação editada com sucesso!*");
    }

    //metodo para remover uma transação
    public void removerTransacao(int id) {
        Transacao transacao = buscarPorId(id);

        if (transacao == null) {
            System.out.println("\n*Transação não encontrada!*");
            return;
        }

        transacoes.remove(transacao);
        System.out.println("\n*Transação removida com sucesso!*");

    }

    //Metodo para calcular saldo atual (receitas - despesas)
    public double calcularSaldo() {
        double totalReceitas = 0;
        double totalDespesas = 0;

        for (Transacao t: transacoes){
            if (t.getTipo() == TipoTransacao.RECEITA){
                totalReceitas+= t.getValor();
            }else {
                totalDespesas+= t.getValor();
            }

        }

        return totalReceitas - totalDespesas;
    }

    //Metodo para calcular total de receitas
    public double calcularTotalReceitas() {
        double total = 0;

        for (Transacao t: transacoes) {
            if (t.getTipo() == TipoTransacao.RECEITA) {
                total+=t.getValor();
            }
        }
        return total;
    }

    //Metodo para calcular total de despesas
    public double calcularTotalDespesas() {
        double total = 0;

        for (Transacao t: transacoes) {
            if (t.getTipo() == TipoTransacao.DESPESA) {
                total+=t.getValor();
            }
        }
        return total;
    }

    //Metodo para calcular despesas em um mês específico
    public double calcularDespesasMes(int mes, int ano) {
        double total = 0;
        for (Transacao t: transacoes) {
            if (t.getTipo() == TipoTransacao.DESPESA &&
                    t.getData().getMonthValue() == mes &&
            t.getData().getYear() == ano) {
                total+= t.getValor();
            }
        }
        return total;
    }


    //Metodo para gerar relatorio mensal
    public void gerarRelatorioAnual(int ano) {
        double totalReceitas = 0.0;
        double totalDespesas = 0.0;

        // Mapas para armazenar valores por categoria
        Map<String, Double> receitasPorCategoria = new HashMap<>();
        Map<String, Double> despesasPorCategoria = new HashMap<>();

        // Percorre todas as transações
        for (Transacao t : transacoes) {
            // Filtra apenas as transações do ano informado
            if (t.getData().getYear() == ano) {

                if (t.getTipo() == TipoTransacao.RECEITA) {
                    totalReceitas += t.getValor();

                    String categoria = t.getCategoria();
                    receitasPorCategoria.put(
                            categoria,
                            receitasPorCategoria.getOrDefault(categoria, 0.0) + t.getValor()
                    );

                } else if (t.getTipo() == TipoTransacao.DESPESA) {
                    totalDespesas += t.getValor();

                    String categoria = t.getCategoria();
                    despesasPorCategoria.put(
                            categoria,
                            despesasPorCategoria.getOrDefault(categoria, 0.0) + t.getValor()
                    );
                }
            }
        }

        double saldoFinal = totalReceitas - totalDespesas;

        System.out.println("\n*** RELATÓRIO ANUAL " + ano + " ***");
        System.out.println("Total de Receitas: R$ " + String.format("%.2f", totalReceitas));
        System.out.println("Total de Despesas: R$ " + String.format("%.2f", totalDespesas));
        System.out.println("Saldo Final: R$ " + String.format("%.2f", saldoFinal));

        // RECEITAS por categoria
        if (!receitasPorCategoria.isEmpty() && totalReceitas > 0) {
            System.out.println("\n*** Receitas por Categoria ***");
            for (Map.Entry<String, Double> entry : receitasPorCategoria.entrySet()) {
                double percentual = (entry.getValue() / totalReceitas) * 100.0;
                System.out.println(
                        entry.getKey() + ": R$ " + String.format("%.2f", entry.getValue()) +
                                " (" + String.format("%.1f", percentual) + "%)"
                );
            }
        }

        // DESPESAS por categoria
        if (!despesasPorCategoria.isEmpty() && totalDespesas > 0) {
            System.out.println("\n*** Despesas por Categoria ***");
            for (Map.Entry<String, Double> entry : despesasPorCategoria.entrySet()) {
                double percentual = (entry.getValue() / totalDespesas) * 100.0;
                System.out.println(
                        entry.getKey() + ": R$ " + String.format("%.2f", entry.getValue()) +
                                " (" + String.format("%.1f", percentual) + "%)"
                );
            }
        }

        System.out.println("***************************\n");
    }


    //Metodo para verificar alerta de orçamento
    public void verificarAlertaOrcamento() {
        LocalDate hoje = LocalDate.now();
        double despesasMesAtual = calcularDespesasMes(hoje.getMonthValue(), hoje.getYear());
        double orcamento = usuarioLogado.getOrcamentoMensal();

        //verifica se já gastou mais de % ou mais do orçamento passado
        double percentualGasto = (despesasMesAtual / orcamento) * 100;

        if (percentualGasto >= 80 && percentualGasto < 100) {
            System.out.println("\n*** ALERTA: Você já gastou " + String.format("%.1f", percentualGasto) + "% do seu orçamento mensal! ***");
            System.out.println(" Orçamento: R$ " + String.format("%.2f", orcamento));
            System.out.println(" Gasto: R$ " + String.format("%.2f", despesasMesAtual));
            System.out.println(" Disponível: R$ " + String.format("%.2f", orcamento - despesasMesAtual));

        } else if(percentualGasto >=100){
            System.out.println("\n*** ALERTA: Você ultrapassou seu orçamento mensal! ***");
            System.out.println(" Orçamento: R$ " + String.format("%.2f", orcamento));
            System.out.println(" Gasto: R$ " + String.format("%.2f", despesasMesAtual));

            double excedente = despesasMesAtual - orcamento;

            System.out.println(" Disponível: R$ 0,00");
            System.out.println(" Você excedeu o orçamento em: R$ " + String.format("%.2f", excedente));
        }

    }


    //Metodo para exibir Saldo atual
    public void exibirSaldo() {
        double saldo = calcularSaldo();
        double receitas = calcularTotalReceitas();
        double despesas = calcularTotalDespesas();

        System.out.println("********** SALDO ATUAL **********");
        System.out.println("Total de Receitas: R$ " + String.format("%.2f" , receitas));
        System.out.println("Total de Despesas: R$ " + String.format("%.2f" , despesas));
        System.out.println("Saldo: R$ " + String.format("%.2f", saldo));
        System.out.println("***************************\n");
    }

}
