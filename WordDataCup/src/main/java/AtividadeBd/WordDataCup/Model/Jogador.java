package AtividadeBd.WordDataCup.Model;

public class Jogador {
<<<<<<< HEAD
	private int numeroCamisa;
=======
	private int numero_da_camisa;
>>>>>>> 5d07769 (Concertando)
	private int inscricao;
	private String posicao;
	private int selecao_id;
	private String nome;
	private int idade;
<<<<<<< HEAD
	
	public Jogador(int n_camisa, int n_inscricao, String posicao, int selecao_id, String nome_atleta, int idade) {
		this.numeroCamisa = n_camisa;
=======
	private int fk_selecao_inscricao;
	private int capitao;

	public Jogador(int n_camisa, int n_inscricao, String posicao, int selecao_id, String nome_atleta, int idade, int fk_selecao_inscricao, int capitao) {
		this.numero_da_camisa = n_camisa;
>>>>>>> 5d07769 (Concertando)
		this.inscricao = n_inscricao;
		this.posicao = posicao;
		this.selecao_id = selecao_id;
		this.nome = nome_atleta;
		this.idade = idade;
<<<<<<< HEAD
	}
	
	public int getNumeroCamisa() {
		return numeroCamisa;
	}
	
	public int getInscricao() {
=======
		this.fk_selecao_inscricao = fk_selecao_inscricao;
		this.capitao = capitao;

	}
	
	public int getNumeroCamisa() {
		return numero_da_camisa;
	}
	
	public int getInscricao() {

>>>>>>> 5d07769 (Concertando)
		return inscricao;
	}
	
	public String getPosicao() {
<<<<<<< HEAD
		return posicao;
	}
	public int getSelecao() {
=======

		return posicao;
	}
	public int getSelecao() {

>>>>>>> 5d07769 (Concertando)
		return selecao_id;
	}
	
	public String getNome() {
<<<<<<< HEAD
=======

>>>>>>> 5d07769 (Concertando)
		return nome;
	}
	public int getIdade() {
		return idade;
	}
<<<<<<< HEAD
}
=======
	public int getFkSelecaoInscricao() {
		return fk_selecao_inscricao;
	}

	public int getCapitao() {
		return capitao;
	}
}

>>>>>>> 5d07769 (Concertando)
