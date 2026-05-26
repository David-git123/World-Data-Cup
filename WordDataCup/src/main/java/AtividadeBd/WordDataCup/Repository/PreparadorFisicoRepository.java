package AtividadeBd.WordDataCup.Repository;

import AtividadeBd.WordDataCup.Model.PreparadorFisico;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PreparadorFisicoRepository {
    private final JdbcTemplate jdbcTemplate;

    public PreparadorFisicoRepository(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserirPreparadorFisico(PreparadorFisico preparadorFisico){
        String sql = "INSERT INTO preparador_fisico (fk_comissao_tecnica_inscricao,especializacao,metodologia_treino) VALUES (?,?,?)";

        jdbcTemplate.update(sql,
                preparadorFisico.getFkComissaoTecnicaInscricao(),
                preparadorFisico.getEspecializacao(),
                preparadorFisico.getMetodologiaTreino()
        );
    }
    public void atualizarPreparadorFisico(PreparadorFisico preparadorFisico){
        String sql = "UPDATE preparador_fisico SET especializacao = ?, metodologia_treino = ? WHERE fk_comissao_tecnica_inscricao = ?";

        jdbcTemplate.update(sql,
                preparadorFisico.getFkComissaoTecnicaInscricao(),
                preparadorFisico.getEspecializacao(),
                preparadorFisico.getMetodologiaTreino()
        );
    }
    public void deletarPreparadorFisico(PreparadorFisico preparadorFisico){
        String sql = "DELETE FROM preparador_fisico WHERE fk_comissao_tecnica_inscricao = ?";

        jdbcTemplate.update(sql,
                preparadorFisico.getFkComissaoTecnicaInscricao()
        );
    }
}
