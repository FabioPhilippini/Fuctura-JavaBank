package entitie;

import java.util.List;

public abstract class Conta {
	private Pessoa pessoa;
	private int numero;
	private double limite;
	private String agencia;
	protected double saldo;
	private boolean contaAtiva = false;

	public Conta() {
	}

	public Conta(Pessoa pessoa, int numero, double limite, String agencia, double saldo) {	
		this.pessoa = pessoa;
		this.numero = numero;
		this.limite = limite;
		this.agencia = agencia;
		this.saldo = saldo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public int getNumero() {
		return numero;
	}
    
	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public double getSaldo() {
		return saldo;
	}
    
	public boolean getContaAtiva() {
		return contaAtiva;
	}

	public void setContaAtiva(boolean contaAtiva) {
		this.contaAtiva = contaAtiva;
	}

	public void depositar(double quantia) {
		if (quantia > 0) {
			this.saldo += quantia;
		} 
		else {
			System.err.println("Impossivel depositar");
		}
	}

	public boolean sacar(double quantia) {
		if (this.saldo >= quantia && quantia > 0) {
			this.saldo -= quantia;
			System.out.println("Saque efetuado.");
			return true;
		} 
		else if (quantia <= 0) {
			System.err.println("Valor inválido.");
			return false;
		} 
		else {
			System.err.println("Saldo Insuficiente.");
			return false;
		}
	}
    
	public void exibirDados() {
		System.out.println("Número da Conta: " + this.getNumero());
		System.out.println("Número da Agência: " + this.getAgencia());
		System.out.println("Titular da conta: " + this.getPessoa().getNome());
		System.out.println("CPF: " + this.getPessoa().getCPF());
		System.out.println("Limite: " + this.getLimite());
		System.out.println("Saldo: " + this.getSaldo());
	}

	public void transferir(Conta outraConta, double quantia) {
		sacar(quantia);
		outraConta.depositar(quantia);
	}
	
	public static boolean contaExiste(List<Conta> listaContas,int numConta) {
		Conta conta = listaContas.stream().filter(x -> x.getNumero() == numConta).findFirst().orElse(null);
		return conta != null;
	}
	
	public static Conta contaNum(List<Conta> listaContas,int numConta) {
		Conta conta = listaContas.stream().filter(x -> x.getNumero() == numConta).findFirst().orElse(null);
		return conta;
	}
	
	public String toString() {
		return "[ Titular: " + pessoa + " - Conta: " + numero + " - Agencia: " + agencia + " - Limite: " + limite
				+ " - Saldo: " + saldo;
	}
	
    
}
