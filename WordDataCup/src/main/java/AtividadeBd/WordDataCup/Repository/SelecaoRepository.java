package AtividadeBd.WordDataCup.Repository;

import AtividadeBd.WordDataCup.Model.Selecao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SelecaoRepository {

    private final JdbcTemplate jdbcTemplate;

    public SelecaoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserirSelecao(Selecao selecao) {

        String sql = "INSERT INTO SELECAO (inscricao, paisDeOrigem, mediaIdade, mediaGols, rankingFifa) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                selecao.getInscricao(),
                selecao.getPaisDeOrigem(),
                selecao.getMediaIdade(),
                selecao.getMediaGols(),
                selecao.getRankingFifa()
        );
    }
}