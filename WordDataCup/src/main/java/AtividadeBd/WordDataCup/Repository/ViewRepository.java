package AtividadeBd.WordDataCup.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ViewRepository {

    private final JdbcTemplate jdbcTemplate;

    public ViewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // VIEW 1
    public List<Object[]> listarEscalacaoEstadios(){

        String sql = "SELECT * FROM v_escalacao_estadios";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Object[]{
                        rs.getString("jogador"),
                        rs.getString("estadio"),
                        rs.getString("sede"),
                        rs.getInt("id_jogo")
                }
        );
    }

    // VIEW 2
    public List<Object[]> listarSelecoesTopo(){

        String sql = "SELECT * FROM v_selecoes_topo";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Object[]{
                        rs.getString("selecao"),
                        rs.getInt("ranking_da_fifa"),
                        rs.getString("mascote")
                }
        );
    }
}