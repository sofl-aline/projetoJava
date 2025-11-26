package com.financeiro.enums;

/*Enum de representação das categorias de Receita
O que vai dentro dos () é exibido para o usuário no console!*/

public enum CategoriaDespesa {

    MORADIA ("Moradia (Aluguel, Condomínio, Água, Energia elétrica, etc)"),
    ALIMENTACAO ("Alimentação (Restaurante, Supermercado, Feira)"),
    TRANSPORTE ("Transporte (Veículo, Seguro Carro, Vale-Transporte, Combustível, Estacionamento, Manutenção)"),
    SAUDE ("Saúde (Plano de saúde, Academia, Consulta médica, Exames, Medicamentos, Terapia, Tratamemnto)"),
    EDUCACAO ("Educação (Escola, Cursos, Material didático, Livros, Palestras e Workshops)"),
    LAZER ("Lazer e Cultura (Assinaturas, Cinema, Shows, Viagens e passeios, Festas e eventos)"),
    VESTIUARIO("Vestuário e Cuidados Pessoais (Roupas, Calçados, Estética, Higiene pessoal, cosméticos)"),
    CARTOES ("Cartões (Contas, parcelas, tarifas)"),
    PET("Pets (Banho/Tosa, Alimentação, Brinquedos, Remédios/Vermífugos, Veterinário)"),
    OUTROS("Outros/Diversos");

    private String descricao;

    //Construtor do enum:
    CategoriaDespesa(String descricao) { this.descricao = descricao;}

    //metodo get para pegar descrição
    public String getDescricao() { return descricao;}

}

