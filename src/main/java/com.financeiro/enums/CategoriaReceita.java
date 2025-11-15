package com.financeiro.enums;

/*Enum de representação das categorias de Receita
O que vai dentro dos () é exibido para o usuário no console!*/

public enum CategoriaReceita {
    TRABALHO ("Trabalho (Salário, Pró-labore, Extras)"),
    INVESTIMENTOS ("Investimentos (Dividendos, juros de aplicação)"),
    PATRIMONIOS ("Aluguéis e Patrimônios (Aluguel, Venda de bens)"),
    SERVICOS ("Serviços (Prestação de serviço, Freelance)"),
    OUTROS ("Outros/Diversos");

    private final String descricao;

    //Construtor do enum:
    CategoriaReceita(String descricao) { this.descricao = descricao;}

    //metodo get para pegar descrição
    public String getDescricao() { return descricao;}

}
