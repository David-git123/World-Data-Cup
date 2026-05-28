package AtividadeBd.WordDataCup.Model; 

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable 
@Table(name = "vw_classificacao_grupos") 
public class ClassificacaoGrupoView {

    @Id 
    private String letraIdentificadora; 
    
    private String lugar1;
    private String lugar2;
    private String lugar3;
    private String lugar4;

    // Construtor padrão exigido pelo JPA
    public ClassificacaoGrupoView() {}

    // Getters apenas (Não precisa de Setters porque é Immutable)
    public String getLetraIdentificadora() { return letraIdentificadora; }
    public String getLugar1() { return lugar1; }
    public String getLugar2() { return lugar2; }
    public String getLugar3() { return lugar3; }
    public String getLugar4() { return lugar4; }
}