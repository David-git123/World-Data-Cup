package AtividadeBd.WordDataCup.Repository;

import AtividadeBd.WordDataCup.Model.Treinador;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TreinadorRepository {
    private final JdbcTemplate jdbcTemplate;

    public TreinadorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserirTreinador(Treinador treinador){
        String sql = "INSERT INTO treinador (fk_comissao_tecnica_inscricao,estilo_jogo,esquema_tatico_pref,metodologia_treino) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql,
                treinador.getFkComissaoTecnicaInscricao(),
                treinador.getEstiloDeJogo(),
                treinador.getEsquemaTaticoPref(),
                treinador.getMetodologiaTreino()
        );

    }
    public void atualizarTreinador(Treinador treinador){
        String sql = "UPDATE  treinador SET estilo_de_jogo = ?, esquema_tatico_pref = ? WHERE fk_inscricao_selecao = ?";
        jdbcTemplate.update(sql,
                treinador.getEstiloDeJogo(),
                treinador.getEsquemaTaticoPref(),
                treinador.getMetodologiaTreino(),
                treinador.getFkComissaoTecnicaInscricao()
        );

    }

    public void deletarTreinador(Treinador treinador){
        String sql = "DELETE FROM treiandor WHERE fk_comissao_tecnica_inscricao = ?";
        jdbcTemplate.update(sql,
                treinador.getFkComissaoTecnicaInscricao()
        );

    }

}
