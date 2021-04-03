package entitie;

public class Pessoa {
	private String CPF;
	private String nome;

	public Pessoa() {
	}
	
	public Pessoa(String cPF, String nome) {
		CPF = cPF;
		this.nome = nome;
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

	@Override
	public String toString() {
		return nome +  " - CPF: " + CPF;
	}
}
