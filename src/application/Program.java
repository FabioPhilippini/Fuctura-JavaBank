package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entitie.Conta;
import entitie.ContaCorrente;
import entitie.ContaPoupanca;
import entitie.Pessoa;
import exceptions.DomainException;

public class Program {
	public static List<Conta> listaContas = new ArrayList<>();

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		int opcao = obterOpcao();

		while (opcao != 9) {
			switch (opcao) {
			case 1:
				listarContas();
				break;
			case 2:
				inserirConta();
				break;
			case 3:
				sacar();
				break;
			case 4:
				depositar();
				break;
			case 5:
				transferir();
				break;
			case 6:
				exibirDados();
				break;
			case 7:
				dadosCliente();
				break;
			case 8:
				removerConta();
				break;
			default:
				System.err.println("Op��o Inv�lida");
				System.out.println();
			}
			opcao = obterOpcao();
		}
		System.out.println("Obrigado por utilizar nossos servi�os");
		System.out.println();
       
		sc.close();
	}

	private static void dadosCliente() {
		Scanner sc = new Scanner(System.in);
		System.out.println("-Dados do cliente-");
		System.out.print("Inserir o n�mero da conta do cliente: ");
		int numConta = sc.nextInt();
		Conta conta = Conta.contaNum(listaContas,numConta);
		if (conta == null) {
			System.err.println("Conta n�o encontrada");
		} 
		else {
			conta.dadosCliente();
		}
		System.out.println();
		
	}

	private static void removerConta() {
		Scanner sc = new Scanner(System.in);
		System.out.println("-Remover conta-");
		System.out.print("N�mero da conta a ser removida: ");
		int numConta = sc.nextInt();
		Conta conta = Conta.contaNum(listaContas,numConta);	
		if(conta != null && conta.getSaldo() ==0) {
			System.out.print("Deseja de fato remover a conta (S/N)?: ");
			char resp = sc.next().charAt(0);
			if(resp =='S' || resp =='s') {
				listaContas.remove(conta);
				System.out.println("Conta removida com sucesso.");
			}
			else {
					System.out.println("Opera��o abortada");		
			}
		}
		else {
			System.err.println("Conta n�o p�de ser removida.");
		}
		System.out.println();		
	}

	private static void exibirDados() {
		Scanner sc = new Scanner(System.in);
		System.out.println("-Dados da conta-");
		System.out.print("Inserir o n�mero da conta: ");
		int numConta = sc.nextInt();
		Conta conta = Conta.contaNum(listaContas,numConta);
		if (conta == null) {
			System.err.println("Conta n�o encontrada");
		} 
		else {
			conta.exibirDados();
		}
		System.out.println();

	}

	private static void listarContas() {
		System.out.println("Lista de contas: ");
		if (listaContas.isEmpty()) {
			System.err.println("Nenhuma conta cadastrada \n");	
		} 
		else {			
			for (Conta c : listaContas) {
		    System.out.println(c.toString());
		    System.out.println();
			}
		}

	}
 
	private static void transferir() {
		Scanner sc = new Scanner(System.in);
		System.out.println("-Transfer�ncia-");
		System.out.print("Digite o n�mero da conta: ");
		int numConta = sc.nextInt();
		Conta conta = Conta.contaNum(listaContas,numConta);
		if (conta == null) {
			System.err.println("Conta n�o encontrada");
		}

		else {
			System.out.print("N�mero da conta que vai receber a transfer�ncia: ");
			int numContaTransferencia = sc.nextInt();
			Conta outraConta = Conta.contaNum(listaContas,numContaTransferencia);
			if (outraConta != null) {
				System.out.print("Valor a ser transferido: ");
				double valorTransferencia = sc.nextDouble();
				try {
				conta.transferir(outraConta, valorTransferencia);
				}
				catch(DomainException e) {
					System.err.println("Erro no procedimento: " + e.getMessage() + "\n");					
				}
				System.out.println("Transfer�ncia efetuada para conta: " + outraConta.getNumero());
			}
			else {
				System.err.print("Conta para transfer�ncia n�o encontrada \n");
			}		
		}

	}

	private static void depositar() {
		Scanner sc = new Scanner(System.in);
		System.out.println("-Dep�sito-");
		System.out.print("Digite o n�mero da conta: ");
		int numConta = sc.nextInt();
		Conta conta = Conta.contaNum(listaContas,numConta);
		if (conta == null) {
			System.err.println("Conta n�o encontrada");
		} 
		else {
			System.out.print("Valor a ser depositado: ");
			double valorDeposito = sc.nextDouble();
			try {
				conta.depositar(valorDeposito);
				System.out.println("Deposito efetuado com sucesso. \n");
			}
			catch(DomainException e) {
				System.err.println("Erro no procedimento: " + e.getMessage() + "\n");
			}
		}

	}

	private static void sacar() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite o n�mero da conta: ");
		int numConta = sc.nextInt();    
		Conta conta = Conta.contaNum(listaContas,numConta);
		if (conta == null) {
			System.err.println("Conta n�o encontrada");
		} 
		else {
			System.out.print("Valor a ser sacado: ");
			double valorSaque = sc.nextDouble();
			try {
				conta.sacar(valorSaque);
			}
			catch(DomainException e){
				System.err.println("Erro no procedimento: " + e.getMessage() + "\n");
			}
			System.out.println();
		}
	}

	private static void inserirConta() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Dados a serem inseridos");
		System.out.print("Titular: ");
		String nome = sc.nextLine();

		System.out.print("CPF: ");
		String cpf = sc.nextLine();
		while(Pessoa.formatoCPF(cpf) !=true) {
			System.out.println("Formato de CPF inv�lido. Tente novamente: ");
			cpf=sc.nextLine();		
		}
		
		System.out.print("Telefone: ");
		String tel = sc.nextLine();	
		while(Pessoa.formatoTelefone(tel) !=true) {
			System.out.println("Formato de telefone inv�lido. Tente novamente: ");
			tel=sc.nextLine();
		}
		
		System.out.print("Email: ");
		String email = sc.nextLine();	
		while(Pessoa.formatoEmail(email) !=true) {
			System.out.println("Formato de email inv�lido. Tente novamente: ");
			email=sc.nextLine();
	      }
		
		Pessoa pessoa = new Pessoa(nome, cpf,tel,email);

		System.out.print("N�mero da conta: ");
		int num = sc.nextInt();
		while(Conta.contaNum(listaContas,num) != null) {
			System.out.print("Conta j� existe. Entre com outro n�mero: ");
			num =sc.nextInt();
		}
        
		System.out.print("Limite da conta: ");
		double limite = sc.nextDouble();

		System.out.print("Ag�ncia: ");
		sc.nextLine();
		String agencia = sc.nextLine();

		System.out.print("Saldo: ");
		double saldo = sc.nextDouble();
		System.out.print("Conta corrente ou poupan�a (C/P): ");
		char resp = sc.next().charAt(0);
		if (resp == 'c' || resp == 'C') {
			System.out.print("De quantos R$ � o desconto na hora do saque: ");
			double desc = sc.nextDouble();
			listaContas.add(new ContaCorrente(pessoa, num, limite, agencia, saldo, desc));
			System.out.println();
		} 
		else {
			System.out.print("De quantos % � a taxa de juros do dep�sito: ");
			double bonus = sc.nextDouble();
			listaContas.add(new ContaPoupanca(pessoa, num, limite, agencia, saldo, bonus));
			System.out.println();
		}
	}

	public static int obterOpcao() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Bem vindo ao JavaBank");
		System.out.println("Informe a op��o desejada");
		System.out.println("1-Listar contas.");
		System.out.println("2-Inserir conta.");
		System.out.println("3-Sacar.");
		System.out.println("4-Depositar.");
		System.out.println("5-Transferir.");
		System.out.println("6-Exibir dados da conta desejada.");
		System.out.println("7-Dados do titular da conta.");
		System.out.println("8-Remover Conta.");
		System.out.println("9-Sair.");
		System.out.println();
		int opcao = sc.nextInt();
		System.out.println();
		return opcao;
	}
}