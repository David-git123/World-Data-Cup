package AtividadeBd.WordDataCup.Repository;

import AtividadeBd.WordDataCup.Model.Mascote;
import org.springframework.jdbc.core.JdbcTemplate;

public class MascoteRepository {

    private final JdbcTemplate jdbcTemplate;

    public MascoteRepository(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserirMascote(Mascote mascote){
        String sql = "INSERT INTO mascote (id,nome,fk_selecao_inscricao) VALUES (?,?,?)";
        jdbcTemplate.update(sql,
                mascote.getId(),
                mascote.getNome(),
                mascote.getfkSelecaoInscricao()
        );
    }

    public void atualizarMascote(Mascote mascote){
        String sql = "UPDATE mascote SET nome = ?, fk_selecao_inscricao = ? WHERE id = ? ";
        jdbcTemplate.update(sql,
                mascote.getNome(),
                mascote.getfkSelecaoInscricao()
        );
    }

    public void deletarMascote(Mascote mascote){
        String sql = "DELETE from mascote WHERE id = ?";
        jdbcTemplate.update(sql,
                mascote.getId()
        );
    }


}
