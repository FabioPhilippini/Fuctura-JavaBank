package entitie;

public class ContaCorrente extends Conta {
	private double desconto;

	public ContaCorrente() {
		super();
	}

	public ContaCorrente(Pessoa pessoa, int numero, double limite, String agencia, double saldo, double desconto) {
		super(pessoa, numero, limite, agencia, saldo);
		this.desconto = desconto;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	@Override
	public boolean sacar(double quantia) {
		if (quantia > 0) {
			quantia += desconto;
		}
		super.sacar(quantia);
		return true;
	}

	@Override
	public void exibirDados() {
		super.exibirDados();
		System.out.println("Tipo: Conta Corrente");
	}

	@Override
	public String toString() {
		return super.toString() + " - Desconto: " + String.format("%.2f", desconto) + " - " + "Tipo: Conta Corrente"
				+ " ]";
	}
}