package AtividadeBd.WordDataCup.Model;

public class Selecao {
	

	private int inscricao;
	private int media_idade;
	private int media_gols;
	private int ranking_da_fifa;
	private String continente;
	private String fk_grupo_letra_identificacao;
	private int pontuacao;
	private String nome;

	public Selecao(int n_inscricao, int mediaIdade, int mediaGols, int rankingFifa,String continente, int pontuacao, String nome,
				   String fk_grupo_letra_identificacao) {
		this.inscricao = n_inscricao;
		 this.media_idade = mediaIdade;
		 this.media_gols = mediaGols;
		 this.ranking_da_fifa = rankingFifa;
		 this.continente = continente;
		 this.pontuacao = pontuacao;
		 this.nome = nome;
		 this.fk_grupo_letra_identificacao = fk_grupo_letra_identificacao;
	}
	
	public int getInscricao() {
		return inscricao;
	}

	public int getMediaIdade() {
		return media_idade;
	}

	public int getMediaGols() {
		return media_gols;
	}

	public String getContinente() {
		return continente;
	}

	public String getFk_grupo_letra_identificacao() {
		return fk_grupo_letra_identificacao;
	}
	public int getPontuacao() {
		return pontuacao;
	}
	public String getNome() {
		return nome;
	}
	public int getRankingFifa() {
		return ranking_da_fifa;
	}
}
