package AtividadeBd.WordDataCup.Model;

public class AuxiliarTecnico {
    private int fkComissaoTecnicaInscricao;
    private String especialidade;
    private String focoSetorial;

    public AuxiliarTecnico(int fkComissaoTecnicaInscricao, String especialidade, String focoSetorial){
        this.fkComissaoTecnicaInscricao = fkComissaoTecnicaInscricao;
        this.especialidade = especialidade;
        this.focoSetorial = focoSetorial;
    }

    public int getFkComissaoTecnicaInscricao() {
        return fkComissaoTecnicaInscricao;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getFocoSetorial() {
        return focoSetorial;
    }

    public void setFkComissaoTecnicaInscricao(int fkComissaoTecnicaInscricao) {
        this.fkComissaoTecnicaInscricao = fkComissaoTecnicaInscricao;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setFocoSetorial(String focoSetorial) {
        this.focoSetorial = focoSetorial;
    }
}
