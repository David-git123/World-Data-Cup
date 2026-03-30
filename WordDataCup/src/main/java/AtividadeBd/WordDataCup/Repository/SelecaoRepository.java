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

<<<<<<< HEAD
        String sql = "INSERT INTO Selecao (inscricao, paisDeOrigem, mediaIdade, mediaGols, rankingFifa) VALUES (?, ?, ?, ?, ?)";
=======
        String sql = "INSERT INTO Selecao (inscricao, paisDeOrigem, mediaIdade, mediaGols, rankingFifa, continete, pontuacao, nome, fk_grupo_letra_identificadora) VALUES (?, ?, ?, ?, ?,?,?,?,?)";
>>>>>>> 5d07769 (Concertando)

        jdbcTemplate.update(sql,
                selecao.getInscricao(),
                selecao.getPaisDeOrigem(),
                selecao.getMediaIdade(),
                selecao.getMediaGols(),
                selecao.getRankingFifa()
        );
    }

    public void atualizarSelecao(Selecao selecao) {

<<<<<<< HEAD
        String sql = "UPDATE Selecao SET paisDeOrigem = ?, mediaIdade =?, mediaGols = ?, rankingFifa = ? WHERE inscricao = ?";
=======
        String sql = "UPDATE Selecao SET paisDeOrigem = ?, mediaIdade =?, mediaGols = ?, rankingFifa = ? , continente = ?, pontuacao = ? , nome = ?, fk grupo_letra_identificadora = ? WHERE inscricao = ?";
>>>>>>> 5d07769 (Concertando)

        jdbcTemplate.update(sql,
                selecao.getInscricao(),
                selecao.getPaisDeOrigem(),
                selecao.getMediaIdade(),
                selecao.getMediaGols(),
                selecao.getRankingFifa(),
                selecao.getInscricao()
        );
    }


    public void deletarSelecao(Selecao selecao) {

        String sql = "DELETE FROM Selecao WHERE inscricao = ?";

        jdbcTemplate.update(sql,
                selecao.getInscricao()
        );
    }
}