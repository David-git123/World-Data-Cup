package AtividadeBd.WordDataCup.Model;

public class Estadio {
    private int id;
    private String cidade;
    private int capacidadeMaxima;
    private String nome;
    private String fkPaisSedeNomePais;


    public Estadio(int id, String cidade, int capacidadeMaxima, String nome, String fkPaisSedeNomePais){
        this.id = id;
        this.capacidadeMaxima = capacidadeMaxima;
        this.nome = nome;
        this.cidade = cidade;
        this.fkPaisSedeNomePais = fkPaisSedeNomePais;
    }

    public int getId() {
        return id;
    }

    public String getCidade() {
        return cidade;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public String getNome() {
        return nome;
    }

    public String getFkPaisSedeNomePais() {
        return fkPaisSedeNomePais;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFkPaisSedeNomePais(String fkPaisSedeNomePais) {
        this.fkPaisSedeNomePais = fkPaisSedeNomePais;
    }


}
