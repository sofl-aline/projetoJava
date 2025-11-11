package service;

import model.Transacao;
import model.TipoTransacao;
import java.util.ArrayList;
import java.util.List;

public class SistemaFinanceiro {
    private List<Transacao> transacoes = new ArrayList<>();

    public void adicionarTransacao(Transacao t) {
        transacoes.add(t);
    }

    public double calcularSaldo() {
        double saldo = 0;
        for (Transacao t : transacoes) {
            if (t.getTipo() == TipoTransacao.RECEITA) {
                saldo += t.getValor();
            } else {
                saldo -= t.getValor();
            }
        }
        return saldo;
    }

    public void listarTransacoes() {
        for (Transacao t : transacoes) {
            System.out.println(t);
        }
    }
}
