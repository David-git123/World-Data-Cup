package AtividadeBd.WordDataCup.Model;

public class Jogador {
	private int numero_da_camisa;
	private int inscricao;
	private String posicao;
	private String nome;
	private int idade;
	private int fk_selecao_inscricao;
	private int capitao;

	public Jogador(int n_camisa, int n_inscricao, String posicao, String nome_atleta, int idade, int fk_selecao_inscricao, int capitao) {
		this.numero_da_camisa = n_camisa;
		this.inscricao = n_inscricao;
		this.posicao = posicao;
		this.nome = nome_atleta;
		this.idade = idade;
		this.fk_selecao_inscricao = fk_selecao_inscricao;
		this.capitao = capitao;

	}
	
	public int getNumeroCamisa() {
		return numero_da_camisa;
	}
	
	public int getInscricao() {

		return inscricao;
	}
	
	public String getPosicao() {

		return posicao;
	}

	
	public String getNome() {

		return nome;
	}
	public int getIdade() {
		return idade;
	}
	public int getFkSelecaoInscricao() {
		return fk_selecao_inscricao;
	}

	public int getCapitao() {
		return capitao;
	}

	public void setNumero_da_camisa(int numero_da_camisa) {
		this.numero_da_camisa = numero_da_camisa;
	}

	public void setInscricao(int inscricao) {
		this.inscricao = inscricao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public void setFk_selecao_inscricao(int fk_selecao_inscricao) {
		this.fk_selecao_inscricao = fk_selecao_inscricao;
	}

	public void setCapitao(int capitao) {
		this.capitao = capitao;
	}
}

