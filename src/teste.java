import trabalholpoo.Banco;
import trabalholpoo.IBanco;
import trabalholpoo.TiposConta;

public class teste {

    public static void main(String[] args) {

        IBanco banco = new Banco();
        banco.cadastraCliente("1", "Ana");
        banco.setEndereco("1", "Primeira Rua", 11);
        banco.setConta("1", TiposConta.MINIMA);
        banco.depositar("1", 1_100.00);
        banco.sacar("1", 550.00);
        banco.cobrarTaxas();
        banco.imprimirRelatorio();

        banco.cadastraCliente("2", "Bia");
        banco.setEndereco("2", "Segunda Rua", 22);
        banco.setConta("2", TiposConta.STANDARD);
        banco.setChequeEspecial("2", 1_100.00);
        banco.depositar("2", 2_200.00);
        banco.sacar("2", 3_000.00);
        banco.cobrarTaxas();
        banco.imprimirRelatorio();

        banco.cadastraCliente("3", "Carla");
        banco.setEndereco("3", "Terceira Rua", 33);
        banco.setConta("3", TiposConta.UM_PORCENTO);
        banco.setChequeEspecial("2", 1_000_000.00);
        banco.depositar("2", 3_300_000.00);
        banco.cobrarTaxas();
        banco.imprimirRelatorio();

        banco.sacar("2", 3_000.00);
        banco.cobrarTaxas();
        banco.imprimirRelatorio();

        banco.cobrarTaxas();
        banco.imprimirRelatorio();
        banco.cobrarTaxas();
        banco.imprimirRelatorio();
        banco.cobrarTaxas();
        banco.imprimirRelatorio();

    }
}
