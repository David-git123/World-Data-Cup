package AtividadeBd.WordDataCup.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProcedureRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProcedureRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void atualizarPontuacaoSelecao(
            int inscricao,
            String resultado,
            int gols
    ){

        String sql = "CALL atualizar_pontuacao_selecao(?, ?, ?)";

        jdbcTemplate.update(
                sql,
                inscricao,
                resultado,
                gols
        );
    }

    public void atualizarLugaresGrupo(char letra){

        String sql = "CALL atualizar_lugares_grupo(?)";

        jdbcTemplate.update(
                sql,
                letra
        );
    }
}