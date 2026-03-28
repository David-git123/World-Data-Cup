package AtividadeBd.WordDataCup.Model;

public class Jogador {
	private int numeroCamisa;
	private int inscricao;
	private String posicao;
	private String selecao;
	private String nome;
	private int idade;
	
	public Jogador(int numeroCamisa, int inscricao, String posicao, String selecao, String nome, int idade) {
		this.numeroCamisa = numeroCamisa;
		this.inscricao = inscricao;
		this.posicao = posicao;
		this.selecao = selecao;
		this.nome = nome;
		this.idade = idade;
	}
	
	public int getNumeroCamisa() {
		return numeroCamisa;
	}
	
	public int getInscricao() {
		return inscricao;
	}
	
	public String posicao() {
		return posicao;
	}
	public String getSelecao() {
		return selecao;
	}
	
	public String getNome() {
		return nome;
	}
	public int getIdade() {
		return idade;
	}
}
