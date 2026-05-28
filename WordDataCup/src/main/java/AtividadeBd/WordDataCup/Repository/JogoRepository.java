package AtividadeBd.WordDataCup.Repository;

import AtividadeBd.WordDataCup.Model.Jogo;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
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
                jogo.getTipoJogo(),
                jogo.getId()
        );
    }

    public void deletarJogo(Jogo jogo){
        String sql = "DELETE from jogo WHERE id = ?";
        jdbcTemplate.update(sql,
                jogo.getId()
        );
    }
    public List<Jogo> listarTodos(){

        String sql = "SELECT * FROM jogo";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Jogo jogo = new Jogo();
            
            jogo.setId(rs.getInt("id"));
            jogo.setPlacar(rs.getString("placar"));
            
            // Correção aqui: convertendo Timestamp para LocalDateTime
            if (rs.getTimestamp("data") != null) {
                jogo.setData(rs.getTimestamp("data").toLocalDateTime());
            }
            
            jogo.setVencedor(rs.getString("vencedor"));
            jogo.setPerdedor(rs.getString("perdedor"));
            jogo.setTipoJogo(rs.getString("tipo_jogo"));
            
            return jogo;
        });
    }
}
