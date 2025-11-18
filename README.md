# 💰 Sistema de Controle Financeiro Pessoal (Java)

Aplicação em Java voltada para organização financeira pessoal, permitindo o cadastro de receitas e despesas, visualização de saldo, geração de relatórios e controle de orçamento mensal.  
O projeto é executado via console (CLI) e segue uma organização modular com classes para transações, sistema financeiro, menu principal e enums de categorias.

---

## 📌 Funcionalidades

### ✔ Cadastro de operações
- Adicionar **Receitas**
- Adicionar **Despesas**
- Categorias separadas:
  - `CategoriaReceita`
  - `CategoriaDespesa`

### ✔ Consulta e filtros
- Listar todas as transações
- Filtrar transações por mês
- Filtrar por **intervalo de meses**
- Filtrar por categoria
- Buscar transação por ID

### ✔ Edição e exclusão
- Editar transações existentes
- Remover transações com confirmação de segurança

### ✔ Relatórios
- **Relatório Mensal**, contendo:
  - Total de receitas
  - Total de despesas
  - Saldo final
  - Distribuição percentual por categoria
- **Relatório Anual**, contendo:
  - Receitas por categoria
  - Despesas por categoria
  - Percentuais por categoria
  - Saldo anual

### ✔ Controle de orçamento
- Definição de um orçamento mensal
- Alertas automáticos:
  - ⚠ 80% do orçamento atingido
  - 🔥 Orçamento ultrapassado
- Demonstração de valores:
  - Disponível
  - Excedido

---

## 🧱 Arquitetura e Organização

```
src/
└── com.financeiro/
├── view/
│ ├── MenuPrincipal.java
│ └── Main.java
├── model/
│ ├── Transacao.java
│ ├── CategoriaDespesa.java
│ ├── CategoriaReceita.java
│ ├── StatusOrcamento.java
│ ├── ResultadoOrcamento.java
│ └── TipoTransacao.java
└── controller/
└── SistemaFinanceiro.java
```

---

- **MenuPrincipal** controla as interações com o usuário.
- **SistemaFinanceiro** contém toda a lógica de negócio.
- **Transacao** representa cada receita/despesa.
- **Enums** organizam tipos e categorias.
- **ResultadoOrcamento** centraliza cálculos de orçamento.

---

## 🚀 Como executar

### ✔ Pré-requisitos
- **Java 17 ou superior**
- Terminal ou VS Code

### ✔ Passos
1. Clone o repositório:

```bash
git clone https://github.com/<seu-usuario>/<seu-repositorio>.git

```
2. Entre no diretório do projeto:
   
```bash
cd nome-do-projeto
```
3.Compile:

```bash
javac -d bin $(find src -name "*.java")
```
4.Execute:

```bash
java -cp bin com.financeiro.view.Main
```

Exemplos de Uso
▶ Tela inicial

```bash
*** MENU PRINCIPAL ***
1. Adicionar Receita
2. Adicionar Despesa
3. Listar Transações
4. Filtrar por Mês
5. Filtrar por Categoria
6. Editar Transação
7. Remover Transação
8. Ver Saldo
9. Relatório Mensal
10. Definir Orçamento
11. Logout
12. Relatório Anual
```


Relatórios
✔ Exemplo de Relatório Mensal

```bash
*** RELATÓRIO MENSAL 05/2025 ***
Total de Receitas: R$ 5000,00
Total de Despesas: R$ 3200,00
Saldo Final: R$ 1800,00

*** Despesas por Categoria ***
Alimentação: R$ 850,00 (26.5%)
Moradia: R$ 1800,00 (56.2%)
Transporte: R$ 550,00 (17.1%)
```


Exemplo de alerta de orçamento

```bash
*** ALERTA: Você já gastou 82.5% do seu orçamento mensal! ***
Orçamento: R$ 3000,00
Gasto: R$ 2475,00
Disponível: R$ 525,00
```


🧪 Boas práticas aplicadas
Separação clara entre view, modelo e controle

Uso de:
- HashMap para somatório por categoria
- ArrayList para registro de transações
- LocalDate para manipulação de datas
- Enums para padronizar categorias e tipos de transação
- try/catch para validar entradas do usuário
- Lógica de orçamento desacoplada usando ResultadoOrcamento

---

💡 Melhorias futuras
- Persistência em arquivos (.txt, .csv, .json)
- Interface gráfica com JavaFX
- Exportar relatórios para PDF
- Login com múltiplos usuários
- Dashboard com gráficos

---
🤝 Contribuições

Pull requests são bem-vindos!

Se tiver sugestões, abra uma issue.

---

📄 Licença

Este projeto pode ser usado livremente para fins acadêmicos ou pessoais.
