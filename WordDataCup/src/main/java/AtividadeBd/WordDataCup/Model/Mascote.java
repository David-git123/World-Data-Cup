package AtividadeBd.WordDataCup.Model;

public class Mascote {
    private String nome;
    private int fkInscricaoSelecao;
    private int id;

    public Mascote(String nome,int inscricao, int fkInscricaoSelecao){
        this.nome = nome;
        this.id = id;
        this.fkInscricaoSelecao = fkInscricaoSelecao;
    }

    public String getNome() {
        return nome;
    }

    public int getfkSelecaoInscricao() {
        return fkInscricaoSelecao;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFkInscricaoSelecao(int fkInscricaoSelecao) {
        this.fkInscricaoSelecao = fkInscricaoSelecao;
    }

    public void setId(int id) {
        this.id = id;
    }
}
