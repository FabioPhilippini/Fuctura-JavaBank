package entitie;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pessoa {
	private String nome;
	private String CPF;
	private String telefone;
	private String email;
    
	//Regex para padrão
	private static final Pattern padraoCPF = Pattern.compile("([0-9]{3}\\.?){3}\\-?[0-9]{2}");
	private static final Pattern padraoTel = Pattern.compile("^(\\(?[1-9]{2}\\)?\\s)?([1-9]{4,5}\\-[0-9]{4})$");
	private static final Pattern padraoEmail = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" 
	                                                           + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	public Pessoa() {
	}

	public Pessoa(String nome, String cPF, String telefone, String email) {
		super();
		this.nome = nome;
		this.CPF = cPF;
		this.telefone = telefone;
		this.email = email;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
	//Métodos para validar os formatos
	public static boolean formatoCPF(String cpf) {
		Matcher match = padraoCPF.matcher(cpf);
		return match.matches();
	}

	public static boolean formatoTelefone(String telefone) {
		Matcher match = padraoTel.matcher(telefone);
		return match.matches();
	}

	public static boolean formatoEmail(String email) {
		Matcher match = padraoEmail.matcher(email);
		return match.matches();
	}

	@Override
	public String toString() {
		return "Nome: " + nome + " - CPF: " + CPF + " - Telefone: " + telefone + " - Email: " + email;
	}
}
