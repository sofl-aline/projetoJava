# Projeto LP1: Sistema de Controle Financeiro Pessoal

![Java](https://img.shields.io/badge/Java-17%2B-blue?logo=java&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-OpenJFX-orange)

Um sistema de desktop desenvolvido em Java com JavaFX para ajudar o usuário a controlar suas finanças pessoais, registrando receitas e despesas, gerando relatórios mensais e alertando quando o orçamento estiver próximo do limite definido.

## Status do Projeto
🚧 **Em Desenvolvimento** 🚧

## ✨ Funcionalidades

O sistema foi planejado com os seguintes níveis de funcionalidade:

### 🟢 Básicas (Obrigatórias)
* **Cadastro de Receitas:** Permite registrar valor, categoria (salário, bônus, etc.), data e descrição.
* **Cadastro de Despesas:** Permite registrar valor, categoria (alimentação, transporte, lazer...), data e descrição.
* **Listagem de Transações:** Exibe todas as transações com filtros por mês ou categoria.
* **Cálculo de Saldo Atual:** Mostra o resultado de (Receitas Totais - Despesas Totais).
* **Relatório Mensal Simples:** Apresenta o total de entradas, saídas e o saldo final do mês.

### 🟡 Intermediárias (Destaque)
* **Definição de Orçamento:** O usuário pode definir um limite de gasto mensal (ex: R$ 2.000).
* **Alerta de Orçamento:** O sistema emite um alerta visual quando as despesas atingem 80% ou mais do limite definido.
* **Gráficos Visuais:** Exibição de gráficos (Pizza ou Barras) em JavaFX para visualizar despesas por categoria.

### ⚪ Avançadas (Opcionais)
* **Exportar Relatório:** Capacidade de exportar o relatório mensal para o formato PDF (utilizando a biblioteca iText).

## 📸 Telas da Aplicação (Interface Gráfica)

A interface será construída em **JavaFX** e contará com as seguintes telas:

1.  **Tela de Login:** O usuário entra com nome e senha.
2.  **Tela Principal (Dashboard):** Exibe o saldo atual, botões de atalho (adicionar receita/despesa) e a lista/tabela de transações recentes.
3.  **Tela de Nova Transação:** Formulário para cadastro de novas receitas ou despesas (campos: valor, categoria, data, descrição, tipo).
4.  **Tela de Relatórios:** Apresenta os gráficos (pizza e barras) para análise visual dos gastos e receitas.

*(Adicione aqui screenshots das telas quando estiverem prontas)*

| Tela de Login | Tela Principal |
| :---: | :---: |
| *[Imagem da Tela de Login]* | *[Imagem da Tela Principal]* |

## 🏛️ Arquitetura e Modelo de Classes (POO)

O projeto utiliza os princípios da **Programação Orientada a Objetos (POO)** para organizar o código de forma modular, coesa e de fácil manutenção. As principais entidades do sistema são:

* `Usuario`: Representa o usuário do sistema (para a tela de login).
* `Transacao`: Classe que armazena os dados comuns de uma operação (valor, data, categoria, descrição).
* `TipoTransacao` (Enum): Define se a transação é uma `RECEITA` ou `DESPESA`.
* `SistemaFinanceiro`: Classe principal (Controller/Facade) que orquestra as operações de CRUD, cálculos de saldo e geração de relatórios.

## 💾 Persistência de Dados

Para salvar e carregar os dados do usuário (transações, orçamento), o sistema utilizará **Arquivos de Texto**.

* **Formato:** CSV (Comma-Separated Values) ou JSON (usando a biblioteca **Gson**).
* **Leitura/Escrita:** `FileWriter` e `BufferedReader` para manipulação dos arquivos.

## 🛠️ Tecnologias Utilizadas

| Tipo | Ferramenta / Tecnologia |
| :--- | :--- |
| Linguagem | Java 17+ |
| Interface Gráfica | JavaFX |
| Persistência | Arquivos de Texto (CSV/JSON) |
| Bibliotecas (Planejadas) | Gson (para JSON), iText (para PDF) |
| IDE | IntelliJ IDEA / Eclipse |

## 🚀 Como Executar o Projeto

1.  Clone este repositório:
    ```bash
    git clone [URL_DO_SEU_REPOSITORIO_AQUI]
    ```
2.  Abra o projeto na sua IDE de preferência (IntelliJ ou Eclipse).
3.  Configure o SDK do Java (JDK 17 ou superior).
4.  Configure as bibliotecas do JavaFX no seu projeto (se não estiver usando Maven/Gradle, será necessário adicionar os JARs ao build path).
5.  Execute a classe principal que contém o `Application.launch()`.

## 🗺️ Etapas de Desenvolvimento (Roadmap)

-   [x] 1. Planejamento das classes e atributos (modelo POO).
-   [ ] 2. Implementar o CRUD básico (adicionar, listar, excluir transações) no back-end.
-   [ ] 3. Criar as interfaces gráficas (Telas) em JavaFX (FXML).
-   [ ] 4. Conectar a interface JavaFX com o código (Controllers).
-   [ ] 5. Adicionar a persistência de dados em arquivos (CSV/JSON).
-   [ ] 6. Implementar a lógica de relatórios e alertas de orçamento.
-   [ ] 7. (Opcional) Adicionar gráficos e exportação para PDF.

## 📄 Licença

Este projeto é distribuído sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.
````
