package trabalholpoo;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class Banco implements IBanco {

    public Banco(){}

    @Override
    public void cadastraCliente(String cpf, String nome) {
        Conta.get(cpf).setNome(nome);
    }

    @Override
    public void setEndereco(String cpf, String nomeDaRua, int numero) {
        Conta conta = Conta.get(cpf);
        conta.setNomeDaRua(nomeDaRua);
        conta.setNumero(numero);
    }

    @Override
    public void setConta(String cpf, TiposConta tipoContas) {
        Conta conta = Conta.get(cpf);

        if (tipoContas == null) {
            conta.setTipoDaConta(TiposConta.MINIMA);
        }else {
            conta.setTipoDaConta(tipoContas);
        }
    }

    @Override
    public boolean sacar(String cpf, double valor) {
        Conta conta = Conta.get(cpf);

        if (conta.getSaldo() >= valor) {
            conta.setSaldo(conta.getSaldo() - valor);
            return true;

        } else if ((conta.getTipoDaConta().equals(TiposConta.STANDARD) ||
                conta.getTipoDaConta().equals(TiposConta.UM_PORCENTO)) &&
                conta.getSaldo() + conta.getChequeEspecial() >= valor) {
            conta.setSaldo(conta.getSaldo() - valor);
            return true;

        } else {
            return false;
        }
    }

    @Override
    public void depositar(String cpf, double valor) {
        Conta conta = Conta.get(cpf);
        conta.setSaldo(conta.getSaldo() + valor);
    }

    @Override
    public void setChequeEspecial(String cpf, double valor) {
        Conta conta = Conta.get(cpf);

        if (!conta.getTipoDaConta().equals(TiposConta.MINIMA)) {
            conta.setChequeEspecial(valor);
        }else {
            conta.setChequeEspecial(0);
        }
    }

    @Override
    public void cobrarTaxas() {
        for (Map.Entry<String, Conta> contas : Conta.contaHash.entrySet()) {
            if (contas.getValue().getTipoDaConta().equals(TiposConta.STANDARD)) {
                double saldo = contas.getValue().getSaldo();
                double chequeEspecial = (saldo < 0 ? saldo * 0.09 : 0);

                contas.getValue().setSaldo((saldo - 7.00) + chequeEspecial);

            } else if (contas.getValue().getTipoDaConta().equals(TiposConta.UM_PORCENTO)) {
                double saldo = contas.getValue().getSaldo();

                if (contas.getValue().getSaldo() > 0) {
                    contas.getValue().setSaldo((saldo - 30) - saldo * 0.01);
                }else {
                    contas.getValue().setSaldo((saldo - 30) + saldo * 0.01);
                }
            }
        }
    }

    @Override
    public void imprimirRelatorio() {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        for (Map.Entry<String, Conta> contas : Conta.contaHash.entrySet()) {
            System.out.println("-------------------------------------------");
            System.out.println("Nome: " + contas.getValue().getNome());
            System.out.println("Endereço: " + contas.getValue().getNomeDaRua());
            System.out.println("Cpf: " + contas.getValue().getCpf());
            System.out.println("Tipo da Conta: " + contas.getValue().getTipoDaConta());
            System.out.println("Saldo: " + NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                    .format(contas.getValue().getSaldo()));
            System.out.println("Cheque Especial: " + NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                    .format(contas.getValue().getChequeEspecial()));
            System.out.println("-------------------------------------------");
        }
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    }
    
}
