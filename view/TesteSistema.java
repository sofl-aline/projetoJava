package view;

import model.Transacao;
import model.TipoTransacao;
import service.SistemaFinanceiro;
import java.time.LocalDate;

public class TesteSistema {
    public static void main(String[] args) {
        SistemaFinanceiro sistema = new SistemaFinanceiro();

        sistema.adicionarTransacao(new Transacao("Salário", 3000, LocalDate.now(), "Trabalho", TipoTransacao.RECEITA));
        sistema.adicionarTransacao(new Transacao("Aluguel", 1200, LocalDate.now(), "Moradia", TipoTransacao.DESPESA));

        sistema.listarTransacoes();
        System.out.println("Saldo atual: R$" + sistema.calcularSaldo());
    }
}
