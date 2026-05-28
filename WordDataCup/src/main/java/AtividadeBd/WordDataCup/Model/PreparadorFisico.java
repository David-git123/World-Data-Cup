package AtividadeBd.WordDataCup.Model;

public class PreparadorFisico {
   private int fkComissaoTecnicaInscricao;
   private String especializacao;
   private String metodologiaTreino;

   public PreparadorFisico(int fkComissaoTecnicaInscricao, String especializacao, String metodologiaTreino){
       this.fkComissaoTecnicaInscricao = fkComissaoTecnicaInscricao;
       this.especializacao = especializacao;
       this.metodologiaTreino = metodologiaTreino;
   }

   public PreparadorFisico(){
    
   }

    public int getFkComissaoTecnicaInscricao() {
        return fkComissaoTecnicaInscricao;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public String getMetodologiaTreino() {
        return metodologiaTreino;
    }

    public void setFkComissaoTecnicaInscricao(int fkComissaoTecnicaInscricao) {
        this.fkComissaoTecnicaInscricao = fkComissaoTecnicaInscricao;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }

    public void setMetodologiaTreino(String metodologiaTreino) {
        this.metodologiaTreino = metodologiaTreino;
    }
}
