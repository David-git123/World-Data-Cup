package AtividadeBd.WordDataCup.Repository;

import AtividadeBd.WordDataCup.Model.Jogo;
import org.springframework.jdbc.core.JdbcTemplate;

public class JogoRepository {

    private final JdbcTemplate jdbcTemplate;

    public JogoRepository(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserirJogo(Jogo jogo){
        String sql = "INSERT INTO jogo (id,placar,data,vencedor,perdedor,tipo_jogo) VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                jogo.getId(),
                jogo.getPlacar(),
                jogo.getData(),
                jogo.getVencedor(),
                jogo.getPerdedor(),
                jogo.getTipoJogo()
        );
    }

    public void atualizarJogo(Jogo jogo){
        String sql = "UPDATE jogo SET placar = ?, data = ?, vencedor = ?, perdedor = ?, tipo_jogo = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                jogo.getPlacar(),
                jogo.getData(),
                jogo.getVencedor(),
                jogo.getPerdedor(),
                jogo.getTipoJogo()
        );
    }

    public void deletarJogo(Jogo jogo){
        String sql = "DELETE from jogo WHERE id = ?";
        jdbcTemplate.update(sql,
                jogo.getId()
        );
    }
}
