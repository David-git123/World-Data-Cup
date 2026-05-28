package AtividadeBd.WordDataCup.Model;

public class PaisSede {

    private String nomePais;
    private int numeroJogosDopais;
    private int numeroEstadios;
    private String nomeMascote;

    public PaisSede(String nomePais, int numeroJogosDopais, int numeroEstadios,String nomeMascote){
        this.nomePais = nomePais;
        this.numeroJogosDopais = numeroJogosDopais;
        this.numeroEstadios = numeroEstadios;
        this.nomeMascote = nomeMascote;
    }

    public PaisSede(){
        
    }

    public String getNomePais(){
        return nomePais;
    }
    public int getNumeroJogosDopais(){
        return numeroJogosDopais;
    }
    public int getNumeroEstadios(){
        return numeroEstadios;
    }
    public String getNomeMascote(){
        return nomeMascote;
    }

    public void setNomePais(String nomePais) {
        this.nomePais = nomePais;
    }

    public void setNumeroJogosDopais(int numeroJogosDopais) {
        this.numeroJogosDopais = numeroJogosDopais;
    }

    public void setNumeroEstadios(int numeroEstadios) {
        this.numeroEstadios = numeroEstadios;
    }

    public void setNomeMascote(String nomeMascote) {
        this.nomeMascote = nomeMascote;
    }
}
