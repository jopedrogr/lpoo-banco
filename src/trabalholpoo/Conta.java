package trabalholpoo;

import java.util.HashMap;

public class Conta extends Cliente {

    public static HashMap<String, Conta> contaHash = new HashMap<>();

    private double saldo;
    private double chequeEspecial;
    private TiposConta tipoDaConta;

    public static Conta get(String cpf) {
        if (contaHash.containsKey(cpf)) {
            return contaHash.get(cpf);
        }
        return new Conta(cpf);
    }

    public Conta(String cpf) {
        super(cpf);
        contaHash.put(cpf, this);
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getChequeEspecial() {
        return chequeEspecial;
    }

    public void setChequeEspecial(double chequeEspecial) {
        this.chequeEspecial = chequeEspecial;
    }

    public TiposConta getTipoDaConta() {
        return tipoDaConta;
    }

    public void setTipoDaConta(TiposConta tipoDaConta) {
        this.tipoDaConta = tipoDaConta;
    }

}
