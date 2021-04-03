package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entitie.Conta;
import entitie.ContaCorrente;
import entitie.ContaPoupanca;
import entitie.Pessoa;

public class Program {
	public static List<Conta> listaContas = new ArrayList<>();

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		int opcao = obterOpcao();

		while (opcao != 8) {
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
				removerConta();
				break;
			default:
				System.err.println("Opção Inválida");
				System.out.println();
			}
			opcao = obterOpcao();
		}
		System.out.println("Obrigado por utilizar nossos serviços");
		System.out.println();
       
		sc.close();
	}

	private static void removerConta() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Número da conta a ser removida: ");
		int numConta = sc.nextInt();
		Conta conta = Conta.contaNum(listaContas,numConta);	
		if(conta != null && conta.getSaldo() ==0) {
			listaContas.remove(conta);
			System.out.println("Conta removida com sucesso.");
		}
		else {
			System.err.println("Conta não pôde ser removida.");
		}
		System.out.println();
		
	}

	private static void exibirDados() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Inserir o número da conta: ");
		int numConta = sc.nextInt();
		Conta conta = Conta.contaNum(listaContas,numConta);
		if (conta == null) {
			System.err.println("Conta não encontrada");
		} 
		else {
			conta.exibirDados();
		}
		System.out.println();

	}

	private static void listarContas() {
		System.out.println("Lista de contas: ");
		if (listaContas.isEmpty()) {
			System.err.println("Nenhuma conta cadastrada");	
			System.out.println();
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
		System.out.print("Digite o número da conta: ");
		int numConta = sc.nextInt();
		Conta conta = Conta.contaNum(listaContas,numConta);
		if (conta == null) {
			System.err.println("Conta não encontrada");
		}

		else {
			System.out.print("Número da conta que vai receber a transferência: ");
			int numContaTransferencia = sc.nextInt();
			Conta outraConta = Conta.contaNum(listaContas,numContaTransferencia);
			if (outraConta != null) {
				System.out.print("Valor a ser transferido: ");
				double valorTransferencia = sc.nextDouble();
				conta.transferir(outraConta, valorTransferencia);
				System.out.println("Transferência efetuada para conta: " + outraConta.getNumero());
			}
			else {
				System.err.print("Conta para transferência não encontrada");
				System.out.println();
			}		
		}

	}

	private static void depositar() {
		Scanner sc = new Scanner(System.in);

		System.out.print("Digite o número da conta: ");
		int numConta = sc.nextInt();
		Conta conta = Conta.contaNum(listaContas,numConta);
		if (conta == null) {
			System.err.println("Conta não encontrada");
		} 
		else {
			System.out.print("Valor a ser depositado: ");
			double valorDeposito = sc.nextDouble();
			conta.depositar(valorDeposito);
			System.out.println("Deposito efetuado com sucesso.");
			System.out.println();
		}

	}

	private static void sacar() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite o número da conta: ");
		int numConta = sc.nextInt();    
		Conta conta = Conta.contaNum(listaContas,numConta);
		if (conta == null) {
			System.err.println("Conta não encontrada");
		} 
		else {
			System.out.print("Valor a ser sacado: ");
			double valorSaque = sc.nextDouble();
			conta.sacar(valorSaque);
			System.out.println();
		}
	}

	private static void inserirConta() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Dados a serem inseridos");
		System.out.print("Nome Cliente: ");
		String nome = sc.nextLine();

		System.out.print("CPF: ");
		String cpf = sc.nextLine();

		Pessoa pessoa = new Pessoa(cpf, nome);

		System.out.print("Número da conta: ");
		int num = sc.nextInt();
		while(Conta.contaExiste(listaContas,num)) {
			System.out.print("Conta já existe. Entre com outro número: ");
			num =sc.nextInt();
		}
        
		System.out.print("Limite da conta: ");
		double limite = sc.nextDouble();

		System.out.print("Agência: ");
		sc.nextLine();
		String agencia = sc.nextLine();

		System.out.print("Saldo: ");
		double saldo = sc.nextDouble();
		System.out.print("Conta corrente ou poupança (C/P): ");
		char resp = sc.next().charAt(0);
		if (resp == 'c' || resp == 'C') {
			System.out.print("De quantos R$ é o desconto na hora do saque: ");
			double desc = sc.nextDouble();
			listaContas.add(new ContaCorrente(pessoa, num, limite, agencia, saldo, desc));
			System.out.println();
		} 
		else {
			System.out.print("De quantos % é a taxa de juros do depósito: ");
			double bonus = sc.nextDouble();
			listaContas.add(new ContaPoupanca(pessoa, num, limite, agencia, saldo, bonus));
			System.out.println();
		}
	}

	public static int obterOpcao() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Bem vindo ao JavaBank");
		System.out.println("Informe a opção desejada");
		System.out.println("1-Listar contas.");
		System.out.println("2-Inserir conta.");
		System.out.println("3-Sacar.");
		System.out.println("4-Depositar.");
		System.out.println("5-Transferir.");
		System.out.println("6-Exibir dados da conta desejada.");
		System.out.println("7-Remover Conta.");
		System.out.println("8-Sair.");
		System.out.println();
		int opcao = sc.nextInt();
		System.out.println();
		return opcao;
	}
}