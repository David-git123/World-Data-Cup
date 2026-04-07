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

        String sql = "INSERT INTO selecao (inscricao,media_idade, media_gols, ranking_da_fifa, continente, pontuacao, nome, fk_grupo_letra_identificadora) VALUES (?, ?, ?, ?, ?,?,?,?)";

        jdbcTemplate.update(sql,
                selecao.getInscricao(),
                selecao.getMediaIdade(),
                selecao.getMediaGols(),
                selecao.getRankingFifa(),
                selecao.getContinente(),
                selecao.getPontuacao(),
                selecao.getNome(),
                selecao.getFk_grupo_letra_identificacao()
        );
    }

    public void atualizarSelecao(Selecao selecao) {

        String sql = "UPDATE selecao SET media_idade =?, media_gols = ?, ranking_da_fifa= ? , continente = ?, pontuacao = ? , nome = ?, fk_grupo_letra_identificadora = ? WHERE inscricao = ?";

        jdbcTemplate.update(sql,
                selecao.getMediaIdade(),
                selecao.getMediaGols(),
                selecao.getRankingFifa(),
                selecao.getContinente(),
                selecao.getPontuacao(),
                selecao.getNome(),
                selecao.getFk_grupo_letra_identificacao(),
                selecao.getInscricao()
        );
        System.out.println("Atualização feita");

    }


    public void deletarSelecao(Selecao selecao) {

        String sql = "DELETE FROM selecao WHERE inscricao = ?";

        jdbcTemplate.update(sql,
                selecao.getInscricao()
        );
    }
}