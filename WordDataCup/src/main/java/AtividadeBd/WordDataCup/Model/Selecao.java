package AtividadeBd.WordDataCup.Model;

public class Selecao {
	

	private int inscricao;
	private String paisDeOrigem;
	private int mediaIdade;
	private int mediaGols;
	private int rankingFifa;
	
	public Selecao(int inscricao, String paisDeOrigem, int mediaIdade, int mediaGols, int rankingFifa ) {
		this.inscricao = inscricao;
		this.paisDeOrigem = paisDeOrigem;
		 this.mediaIdade = mediaIdade;
		 this.mediaGols = mediaGols;
		 this.rankingFifa = rankingFifa;
	}
	
	public int getInscricao() {
		return inscricao;
	}

	public String getPaisDeOrigem() {
		return paisDeOrigem;
	}

	public int getMediaIdade() {
		return mediaIdade;
	}

	public int getMediaGols() {
		return mediaGols;
	}

	public int getRankingFifa() {
		return rankingFifa;
	}
}	
