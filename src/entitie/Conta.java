package entitie;

import java.util.List;

import exceptions.DomainException;

public abstract class Conta {
	private Pessoa pessoa;
	private int numero;
	private double limite;
	private String agencia;
	protected double saldo;

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
    
	public void depositar(double quantia) {
		validarDeposito(quantia);
		this.saldo += quantia;
	}

	public void validarDeposito(double quantia) {
		if(quantia <= 0) {
			throw new DomainException("Valor inválido.");
		}
	}

	public void sacar(double quantia) {
		validarSaque(quantia);
		this.saldo -= quantia;
		System.out.println("Saque efetuado.");		
	} 
	
	public void validarSaque(double quantia) {
		if(quantia <= 0) {
			throw new DomainException("Valor inválido.");
		}
		if(quantia > this.saldo) {
			throw new DomainException("Saldo Insuficiente.");
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
	
	public static Conta contaNum(List<Conta> listaContas,int numConta) {
		Conta conta = listaContas.stream().filter(x -> x.getNumero() == numConta).findFirst().orElse(null);
		return conta;
	}
	
	public void dadosCliente() {
		System.out.println(pessoa);
	}
	
	public String toString() {
		return "[ Titular: " + pessoa.getNome() + " - Conta: " + numero + " - Agencia: " + agencia + " - Limite: " + limite
				+ " - Saldo: " + saldo;
	}
	  
}
