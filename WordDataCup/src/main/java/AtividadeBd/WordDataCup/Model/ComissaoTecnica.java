package AtividadeBd.WordDataCup.Model;

public class ComissaoTecnica {
    private String nome;
    private String nacionalidade;
    private int inscricao;
    private int fkInscricaoSelecao;

    public ComissaoTecnica(String nome, String nacionalidade, int inscricao, int fkInscricaoSelecao){
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.inscricao = inscricao;
        this.fkInscricaoSelecao = fkInscricaoSelecao;
    }

    public String getNome() {
        return nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public int getInscricao() {
        return inscricao;
    }

    public int getFkInscricaoSelecao() {
        return fkInscricaoSelecao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public void setInscricao(int inscricao) {
        this.inscricao = inscricao;
    }

    public void setFkInscricaoSelecao(int fkInscricaoSelecao) {
        this.fkInscricaoSelecao = fkInscricaoSelecao;
    }
}
