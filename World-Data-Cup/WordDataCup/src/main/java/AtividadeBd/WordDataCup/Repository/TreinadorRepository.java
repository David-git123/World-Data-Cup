package AtividadeBd.WordDataCup.Repository;

import AtividadeBd.WordDataCup.Model.Treinador;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TreinadorRepository {
    private final JdbcTemplate jdbcTemplate;

    public TreinadorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserirTreinador(Treinador treinador){
        String sql = "INSERT INTO treinador (fk_comissao_tecnica_inscricao,estilo_de_jogo,esquema_tatico_pref) VALUES (?,?,?)";

        jdbcTemplate.update(sql,
                treinador.getFkComissaoTecnicaInscricao(),
                treinador.getEstiloDeJogo(),
                treinador.getEsquemaTaticoPref()
        );
    }

    public void atualizarTreinador(Treinador treinador){
        String sql = "UPDATE treinador SET estilo_de_jogo = ?, esquema_tatico_pref = ? WHERE fk_comissao_tecnica_inscricao = ?";

        jdbcTemplate.update(sql,
                treinador.getEstiloDeJogo(),
                treinador.getEsquemaTaticoPref(),
                treinador.getFkComissaoTecnicaInscricao()
        );
    }

    public void deletarTreinador(Treinador treinador){
        String sql = "DELETE FROM treinador WHERE fk_comissao_tecnica_inscricao = ?";
        jdbcTemplate.update(sql,
                treinador.getFkComissaoTecnicaInscricao()
        );

    }

    public List<Treinador> listarTodos(){
        String sql = "SELECT * FROM treinador";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Treinador treinador = new Treinador();
            
            treinador.setFkComissaoTecnicaInscricao(rs.getInt("fk_comissao_tecnica_inscricao"));
            treinador.setEstiloDeJogo(rs.getString("estilo_de_jogo"));
            treinador.setEsquemaTaticoPref(rs.getString("esquema_tatico_pref"));
            
            return treinador;
        });
    }

}
