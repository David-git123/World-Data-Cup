package AtividadeBd.WordDataCup.Model;

public class Selecao {
	

	private int inscricao;
	private String paisDeOrigem;
<<<<<<< HEAD
	private int mediaIdade;
	private int mediaGols;
	private int rankingFifa;
	
	public Selecao(int n_inscricao, String pais_origem, int mediaIdade, int mediaGols, int rankingFifa ) {
		this.inscricao = n_inscricao;
		this.paisDeOrigem = pais_origem;
		 this.mediaIdade = mediaIdade;
		 this.mediaGols = mediaGols;
		 this.rankingFifa = rankingFifa;
=======
	private int media_idade;
	private int media_gols;
	private int ranking_da_fifa;
	private String continente;
	private String fk_grupo_letra_identificacao;
	private int pontuacao;
	private String nome;

	public Selecao(int n_inscricao, String pais_origem, int mediaIdade, int mediaGols, int rankingFifa,String continente, int pontuacao, String nome,
				   String fk_grupo_letra_identificacao) {
		this.inscricao = n_inscricao;
		this.paisDeOrigem = pais_origem;
		 this.media_idade = mediaIdade;
		 this.media_gols = mediaGols;
		 this.ranking_da_fifa = rankingFifa;
		 this.continente = continente;
		 this.pontuacao = pontuacao;
		 this.nome = nome;
		 this.fk_grupo_letra_identificacao = fk_grupo_letra_identificacao;
>>>>>>> 5d07769 (Concertando)
	}
	
	public int getInscricao() {
		return inscricao;
	}

	public String getPaisDeOrigem() {
		return paisDeOrigem;
	}

	public int getMediaIdade() {
<<<<<<< HEAD
		return mediaIdade;
	}

	public int getMediaGols() {
		return mediaGols;
	}

	public int getRankingFifa() {
		return rankingFifa;
	}
}	
=======
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
>>>>>>> 5d07769 (Concertando)
