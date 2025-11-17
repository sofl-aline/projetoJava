package com.financeiro.menu;

import com.financeiro.model.Usuario;
import com.financeiro.enums.*;
import com.financeiro.service.SistemaFinanceiro;
import javax.swing.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/*
Classe responsável por exibir os menus e gerenciar a interação com o usuário.
 -Usa a classe Skanner para ler dados do console e chame os métodos do Sistema Financeiro*/
public class MenuPrincipal {

    //Scanner para ler dados do teclado
    private final Scanner scanner;
    //Sistema Financeiro que gerencia as transações
    private final SistemaFinanceiro sistema;
    //Usuário fictício para simplificar( em um sistema real, existiria cadastro de vários usuários)
    private Usuario usuario;

    //Construtor
    public MenuPrincipal() {
        this.scanner = new Scanner(System.in);
        this.sistema = new SistemaFinanceiro();
        this.usuario = null;
    }

    //Metodo principal que inicia o Sistema
    public void iniciar() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE CONTROLE FINANCEIRO PESSOAL   ║");
        System.out.println("╚════════════════════════════════════════════╝");

        menuInicial();
    }

    //Menu inicial - Login e Criar Conta
    private void menuInicial() {
        while (true) {
            System.out.println("********** MENU INICIAL **********");
            System.out.println("1. Login");
            System.out.println("2.Sair");
            System.out.println("Escolha uma opção: ");

            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    fazerLogin();
                if (usuario != null) {
                    menuPrincipal(); // só vai pro menu principal se fizer o login
                } break;
                case 2:
                    System.out.println("\n* Obrigada por usar o sistema! Até logo! *");
                    scanner.close();
                    return;
                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
            }
        }

    }

    //Metodo para fazer login
    private void fazerLogin() {
        System.out.println("\n********** LOGIN **********");
        System.out.println("Digite seu nome: ");
        String nome = scanner.nextLine();

        System.out.println("Digite sua senha: ");
        String senha = scanner.nextLine();

        if (nome.isEmpty() || senha.isEmpty()) {
            System.out.println("\n* Nome e senha não podem estar vazios! *");
            return;
        }

        usuario = new Usuario(nome, senha);
        sistema.setUsuarioLogado(usuario);
        System.out.println("\n* Login realizado com sucesso! Bem-vindo(a), " + nome + "!*");
    }

    //Menu Principal - após o login
    private void menuPrincipal() {
        while (true) {
            System.out.println("\n********** MENU PRINCIPAL **********");
            System.out.println("1. Adicionar Receita");
            System.out.println("2. Adicionar Despesa");
            System.out.println("3. Listar todas as Transações");
            System.out.println("4. Filtrar Transações por Mês/Meses");
            System.out.println("5. Filtrar Transações por Categoria");
            System.out.println("6. Editar Transação");
            System.out.println("7. Remover Transação");
            System.out.println("8. Ver Saldo Atual");
            System.out.println("9. Gerar Relatório Mensal");
            System.out.println("10. Definir orçamento Mensal");
            System.out.println("11. Logout");
            System.out.println("Escolha uma opção: ");

            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    adicionarReceita(); break;
                case 2:
                    adicionarDespesa(); break;
                case 3:
                    sistema.listarTodasTransacoes(); break;
                case 4:
                    filtrarPorMes(); break;
                case 5:
                    filtrarPorCategoria(); break;
                case 6:
                    editarTransacao(); break;
                case 7:
                    removerTransacao(); break;
                case 8:
                    sistema.exibirSaldo(); break;
                case 9:
                    gerarRelatorioMensal(); break;
                case 10:
                    definirOrcamento(); break;
                case 11:
                    System.out.println("\n* Logout realizado com sucesso! Até Breve! *");
                    usuario = null;
                    sistema.setUsuarioLogado(null);
                    return; //Volta para o menu inicial
                default:
                    System.out.println("\n* Opção inválida! Tente novamente. *");
            }
        }
    }

    //Metodo para adicionar uma receita (opção 1 do Menu Principal)
    private void adicionarReceita() {
        System.out.println("\n********** ADICIONAR RECEITA **********");

        //Escolher categoria
        System.out.println("\nCategoria de Receita: ");
        CategoriaReceita[] categorias = CategoriaReceita.values();
        for (int i=0; i < categorias.length; i++) {
            System.out.println((i+1) + ". " + categorias[i].getDescricao());
        }
        System.out.print("Escolha a categoria (1-" + categorias.length + "): ");
        int opcaoCategoria = lerOpcao();

        if (opcaoCategoria <1 || opcaoCategoria > categorias.length) {
            System.out.println("\nCategoria inválida!");
            return;
        }

        String categoria = categorias[opcaoCategoria -1].getDescricao();

        //Ler valor
        System.out.print("Digite o valor (ex: 1500.00): R$ ");
        double valor = lerValor();
        if (valor <=0) {
            System.out.println("\n* Valor deve ser maior que zero! *");
            return;
        }

        //Ler data
        System.out.print("Digite a data (dd/mm/aaaa) ou pressiona ENTER para hoje: ");
        LocalDate data = lerData();

        //Ler descrição
        System.out.print("Digite a descrição: ");
        String descricao = scanner.nextLine();

        //Adiciona a transação
        sistema.adicionarTransacao(valor, categoria, data, descricao, TipoTransacao.RECEITA);
    }

    //Metodo para adicionar uma despesa (opção 2 do Menu Principal)
    private void adicionarDespesa() {
        System.out.println("\n********** ADICIONAR DESPESA **********");

        //Escolher categoria
        System.out.println("\nCategorias de Despesa: ");
        CategoriaDespesa[] categorias = CategoriaDespesa.values();
        for (int i=0; i < categorias.length; i++) {
            System.out.println((i+1) + ". " + categorias[i].getDescricao());
        }
        System.out.print("Escolha a categoria (1-" + categorias.length + "): ");
        int opcaoCategoria = lerOpcao();

        if (opcaoCategoria < 1 || opcaoCategoria > categorias.length) {
            System.out.println("\nCategoria inválida!");
            return;
        }

        String categoria = categorias[opcaoCategoria -1].getDescricao();

        //Ler valor
        System.out.print("Digite o valor (ex: 150.00): R$ ");
        double valor = lerValor();
        if (valor <= 0) {
            System.out.println("\n* Valor deve ser maior que zero! *");
            return;
        }

        //Ler data
        System.out.print("Digite a data (dd/mm/aaaa) ou pressiona ENTER para hoje: ");
        LocalDate data = lerData();

        //Ler descrição
        System.out.print("Digite a descrição: ");
        String descricao = scanner.nextLine();

        //Adiciona a transação
        sistema.adicionarTransacao(valor, categoria, data, descricao, TipoTransacao.DESPESA);
    }

    //Metodo para filtrar por mês (opção 4 do Menu Principal)
    private void filtrarPorMes() {
        System.out.println("\n********** FILTRAR POR MÊS **********");
        System.out.println("1. Filtrar por um único mês");
        System.out.println("2. Filtrar por intervalo de meses");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();

        if (opcao == 1) {
            // --- FILTRO POR UM ÚNICO MÊS (COMPORTAMENTO ANTIGO) ---
            System.out.print("Digite o mês (1-12): ");
            int mes = lerOpcao();

            if (mes < 1 || mes > 12) {
                System.out.println("\nMês inválido!");
                return;
            }

            System.out.print("Digite o ano (ex: 2025): ");
            int ano = lerOpcao();

            sistema.filtrarPorMes(mes, ano);

        } else if (opcao == 2) {
            // --- FILTRO POR INTERVALO DE MESES ---
            System.out.print("Digite o mês inicial (1-12): ");
            int mesInicial = lerOpcao();

            System.out.print("Digite o mês final (1-12): ");
            int mesFinal = lerOpcao();

            if (mesInicial < 1 || mesInicial > 12 || mesFinal < 1 || mesFinal > 12) {
                System.out.println("\nMês inválido!");
                return;
            }

            // Se o usuário digitar invertido (ex: 8 até 3), a gente corrige
            if (mesInicial > mesFinal) {
                int temp = mesInicial;
                mesInicial = mesFinal;
                mesFinal = temp;
            }

            System.out.print("Digite o ano (ex: 2025): ");
            int ano = lerOpcao();

            sistema.filtrarPorMes(mesInicial, mesFinal, ano);

        } else {
            System.out.println("\nOpção inválida!");
        }
    }



    //Metodo para filtrar por categoria (opção 5 do Menu Principal)
    private void filtrarPorCategoria() {
        System.out.println("\n********** FILTRAR POR CATEGORIA **********");

        // Primeiro escolhe se é categoria de receita ou de despesa
        System.out.println("Escolha o tipo de categoria:");
        System.out.println("1. Categorias de Receita");
        System.out.println("2. Categorias de Despesa");
        System.out.print("Opção: ");
        int tipoOpcao = lerOpcao();

        String categoriaSelecionada;

        if (tipoOpcao == 1) {
            // Listar categorias de RECEITA
            CategoriaReceita[] categorias = CategoriaReceita.values();
            System.out.println("\nCategorias de Receita:");
            for (int i = 0; i < categorias.length; i++) {
                System.out.println((i + 1) + ". " + categorias[i].getDescricao());
            }

            System.out.print("Escolha a categoria (1-" + categorias.length + "): ");
            int opcaoCategoria = lerOpcao();

            if (opcaoCategoria < 1 || opcaoCategoria > categorias.length) {
                System.out.println("\n* Categoria inválida! *");
                return;
            }

            categoriaSelecionada = categorias[opcaoCategoria - 1].getDescricao();

        } else if (tipoOpcao == 2) {
            // Listar categorias de DESPESA
            CategoriaDespesa[] categorias = CategoriaDespesa.values();
            System.out.println("\nCategorias de Despesa:");
            for (int i = 0; i < categorias.length; i++) {
                System.out.println((i + 1) + ". " + categorias[i].getDescricao());
            }

            System.out.print("Escolha a categoria (1-" + categorias.length + "): ");
            int opcaoCategoria = lerOpcao();

            if (opcaoCategoria < 1 || opcaoCategoria > categorias.length) {
                System.out.println("\n* Categoria inválida! *");
                return;
            }

            categoriaSelecionada = categorias[opcaoCategoria - 1].getDescricao();

        } else {
            System.out.println("\n* Opção inválida! *");
            return;
        }

        // Agora chama o serviço usando exatamente a descrição da categoria
        sistema.filtrarPorCategoria(categoriaSelecionada);
    }



    //Metodo para editar uma transação (opção 6 do Menu Principal)
    private void editarTransacao() {
        System.out.println("\n********** EDITAR TRANSAÇÃO ***********");
        sistema.listarTodasTransacoes();

        System.out.print("Digite o ID da transação que deseja editar: ");
        int id = lerOpcao();

        //Verifica se a transação existe
        if (sistema.buscarPorId(id) == null) {
            System.out.println("\nTransação não encontrada!");
            return;
        }

        System.out.print("Digite o novo valor: R$ ");
        double valor = lerValor();

        // ======= ESCOLHA DA CATEGORIA =========
        System.out.println("\nEscolha o tipo de categoria:");
        System.out.println("1. Categorias de Receita");
        System.out.println("2. Categorias de Despesa");
        System.out.print("Opção: ");
        int tipoOpcao = lerOpcao();

        String categoriaSelecionada;

        if (tipoOpcao == 1) {
            CategoriaReceita[] categorias = CategoriaReceita.values();
            System.out.println("\nCategorias de Receita:");
            for (int i = 0; i < categorias.length; i++) {
                System.out.println((i + 1) + ". " + categorias[i].getDescricao());
            }

            System.out.print("Escolha a categoria (1-" + categorias.length + "): ");
            int opcaoCategoria = lerOpcao();

            if (opcaoCategoria < 1 || opcaoCategoria > categorias.length) {
                System.out.println("\n* Categoria inválida! *");
                return;
            }

            categoriaSelecionada = categorias[opcaoCategoria - 1].getDescricao();

        } else if (tipoOpcao == 2) {

            CategoriaDespesa[] categorias = CategoriaDespesa.values();
            System.out.println("\nCategorias de Despesa:");
            for (int i = 0; i < categorias.length; i++) {
                System.out.println((i + 1) + ". " + categorias[i].getDescricao());
            }

            System.out.print("Escolha a categoria (1-" + categorias.length + "): ");
            int opcaoCategoria = lerOpcao();

            if (opcaoCategoria < 1 || opcaoCategoria > categorias.length) {
                System.out.println("\n* Categoria inválida! *");
                return;
            }

            categoriaSelecionada = categorias[opcaoCategoria - 1].getDescricao();

        } else {
            System.out.println("\n* Opção inválida! *");
            return;
        }
        // ======================================

        System.out.print("Digite a nova data (dd/mm/aaaa) ou pressione ENTER para hoje: ");
        LocalDate data = lerData();

        System.out.print("Digite a nova descrição: ");
        String descricao = scanner.nextLine();

        sistema.editarTransacao(id, valor, categoriaSelecionada, data, descricao);
    }



    //Metodo para remover uma transação (opção 7 do Menu Principal)
    private void removerTransacao() {
        System.out.println("\n********** REMOVER TRANSAÇÃO **********");
        sistema.listarTodasTransacoes();

        System.out.print("Digite o ID da transação que deseja remover: ");
        int id = lerOpcao();

        System.out.print("Tem certeza que deseja remover? (S/N): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("S")){
            sistema.removerTransacao(id);
        } else {
            System.out.println("\nOperação cancelada!");
        }
    }

    //Metodo para gerar relatório mensal (opção 9 do Menu Principal)
    private void gerarRelatorioMensal() {
        System.out.println("\n********** GERAR RELATÓRIO MENSAL **********");
        System.out.print("Digite o mês (1-12): ");
        int mes = lerOpcao();

        if(mes < 1 || mes > 12) {
            System.out.println("\nMês inválido!");
            return;
        }

        System.out.print("Digite o ano (ex: 2025): ");
        int ano = lerOpcao();

        sistema.gerarRelatorioMensal(mes, ano);
    }


    //Metodo para definir orçamento mensal (opção 10 do Menu Principal)
    private void definirOrcamento() {
        System.out.println("\n********** definir orçamento mensal **********");
        System.out.print("Digite o valor do orçamento mensal: R$ ");
        double orcamento = lerValor();

        if (orcamento <= 0) {
            System.out.println("\nOrçamento deve ser maior que zero!");
            return;
        }

        usuario.setOrcamentoMensal(orcamento);
        System.out.println("\n* Orçamento mensal definido com sucesso! *");
        System.out.println("Você receberá alertas quando gastar 80% ou mais deste valor.");
    }


    //METODOS DE AUXILIO AOS METODOS PRINCIPAIS (LerOpcao, lerValor, lerData)

    //metodo ler opção (gerado por IA)
    private int lerOpcao() {
        try {
            int opcao = Integer.parseInt(scanner.nextLine()); //força a conversão do que for capturado em int
            return opcao;
        } catch (NumberFormatException e) {
            return -1; //Retorna -1 se o usuário digitar um número não válido(String por exemplo)
        }
    }

    //Metodo lerValor (gerado por IA)
    private double lerValor() {
        try {
            double valor = Double.parseDouble(scanner.nextLine()); //força a conversão do que for capturado em double
            return valor;
        } catch (NumberFormatException e ) {
            return -1; //Retorna -1 se o usuário digitar um número não válido(String por exemplo)
        }
    }

    //Metodo lerData
    private LocalDate lerData() {
        String dataStr = scanner.nextLine();

        //Se o usuário não digitou nada, usa a data de hoje
        if (dataStr.isEmpty()) {
            return LocalDate.now();
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dataStr, formatter);
        } catch (DateTimeException e) {
            System.out.println("\nData inválida! Usando data de hoje.");
            return LocalDate.now();
        }
    }


}
