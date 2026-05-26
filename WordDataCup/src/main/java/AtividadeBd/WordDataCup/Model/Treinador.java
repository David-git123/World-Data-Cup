package AtividadeBd.WordDataCup.Model;

public class Treinador {
    private int fkComissaoTecnicaInscricao;
    private String estiloDeJogo;
    private String esquemaTaticoPref;
    private String metodologiaTreino;

    public Treinador(int fkComissaoTecnicaInscricao,String estiloDeJogo, String esquemaTaticoPref, String metodologiaTreino){
        this.fkComissaoTecnicaInscricao = fkComissaoTecnicaInscricao;
        this.estiloDeJogo = estiloDeJogo;
        this.esquemaTaticoPref = esquemaTaticoPref;
        this.metodologiaTreino = metodologiaTreino;
    }

    public int getFkComissaoTecnicaInscricao() {
        return fkComissaoTecnicaInscricao;
    }

    public String getEstiloDeJogo() {
        return estiloDeJogo;
    }

    public String getEsquemaTaticoPref() {
        return esquemaTaticoPref;
    }

    public String getMetodologiaTreino() {
        return metodologiaTreino;
    }

    public void setFkComissaoTecnicaInscricao(int fkComissaoTecnicaInscricao) {
        this.fkComissaoTecnicaInscricao = fkComissaoTecnicaInscricao;
    }

    public void setEstiloDeJogo(String estiloDeJogo) {
        this.estiloDeJogo = estiloDeJogo;
    }

    public void setEsquemaTaticoPref(String esquemaTaticoPref) {
        this.esquemaTaticoPref = esquemaTaticoPref;
    }

    public void setMetodologiaTreino(String metodologiaTreino) {
        this.metodologiaTreino = metodologiaTreino;
    }
}
