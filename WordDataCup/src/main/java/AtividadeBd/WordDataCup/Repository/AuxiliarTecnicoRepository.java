package AtividadeBd.WordDataCup.Repository;

import AtividadeBd.WordDataCup.Model.AuxiliarTecnico;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuxiliarTecnicoRepository {
    private final JdbcTemplate jdbcTemplate;

    public AuxiliarTecnicoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserirAuxiliarTecnico(AuxiliarTecnico auxiliarTecnico){
        String sql = "INSERT INTO auxiliar_tecnico (fk_comissao_tecnica_inscricao,especialidade,foco_setorial)";
        jdbcTemplate.update(sql,
                auxiliarTecnico.getFkComissaoTecnicaInscricao(),
                auxiliarTecnico.getEspecialidade(),
                auxiliarTecnico.getFocoSetorial()
        );
    }

    public void atualizarAuxiliarTecnico(AuxiliarTecnico auxiliarTecnico){
        String sql = "UPDATE auxiliar_tecnico SET especialidade = ?, foco_setorial = ? WHERE fk_comissao_tecnica_inscricao = ?";
        jdbcTemplate.update(sql,
                auxiliarTecnico.getEspecialidade(),
                auxiliarTecnico.getFocoSetorial(),
                auxiliarTecnico.getFkComissaoTecnicaInscricao()
        );
    }

    public void deletarAuxiliarTecnico(AuxiliarTecnico auxiliarTecnico){
        String sql = "DELETE from auxiliar_tecnico WHERE fk_comissao_tecnica_inscricao = ?";
        jdbcTemplate.update(sql,
                auxiliarTecnico.getFkComissaoTecnicaInscricao()
        );
    }
    public List<AuxiliarTecnico> consultaTotalAuxiliarTecnico(){
        String sql = "SELECT * FROM auxiliar_tecnico";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new AuxiliarTecnico(
                        rs.getInt("fk_comissao_tecnica_inscricao"),
                        rs.getString("especialidade"),
                        rs.getString("foco_setorial")
                )
        );
    }


}
