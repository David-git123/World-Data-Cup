package AtividadeBd.WordDataCup.Model;

public class Jogador {
	private int numeroCamisa;
	private int inscricao;
	private String posicao;
	private int selecao_id;
	private String nome;
	private int idade;
	
	public Jogador(int n_camisa, int n_inscricao, String posicao, int selecao_id, String nome_atleta, int idade) {
		this.numeroCamisa = n_camisa;
		this.inscricao = n_inscricao;
		this.posicao = posicao;
		this.selecao_id = selecao_id;
		this.nome = nome_atleta;
		this.idade = idade;
	}
	
	public int getNumeroCamisa() {
		return numeroCamisa;
	}
	
	public int getInscricao() {
		return inscricao;
	}
	
	public String getPosicao() {
		return posicao;
	}
	public int getSelecao() {
		return selecao_id;
	}
	
	public String getNome() {
		return nome;
	}
	public int getIdade() {
		return idade;
	}
}
