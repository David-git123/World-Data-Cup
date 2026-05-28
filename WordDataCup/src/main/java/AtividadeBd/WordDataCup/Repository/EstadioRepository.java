package AtividadeBd.WordDataCup.Repository;

import AtividadeBd.WordDataCup.Model.Estadio;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EstadioRepository {
    private final JdbcTemplate jdbcTemplate;

    public EstadioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserirEstadio(Estadio estadio){
        String sql = "INSERT INTO estadio (id,cidade,capacidade_maxima,nome,fk_pais_sede_nome_pais) VALUES (?,?,?,?,?)";

        jdbcTemplate.update(
                sql,
                estadio.getId(),
                estadio.getCidade(),
                estadio.getCapacidadeMaxima(),
                estadio.getNome(),
                estadio.getFkPaisSedeNomePais()
        );
    }

    public void atualizarEstadio(Estadio estadio){
        String sql = "UPDATE estadio SET cidade = ?, capacidade_maxima = ?, nome = ?, fk_pais_sede_nome_pais = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                estadio.getCidade(),
                estadio.getCapacidadeMaxima(),
                estadio.getNome(),
                estadio.getFkPaisSedeNomePais(),
                estadio.getId()
        );
    }

    public void deletarEstadio(Estadio estadio){
        String sql = "DELETE FROM estadio WHERE id = ?";
        jdbcTemplate.update(sql,
                estadio.getId()
        );
    }

    public List<Estadio> listarTodos(){

    String sql = "SELECT * FROM estadio";

    return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new Estadio(
                    rs.getInt("id"),
                    rs.getString("cidade"),
                    rs.getInt("capacidade_maxima"),
                    rs.getString("nome"),
                    rs.getString("fk_pais_sede_nome_pais")
            )
    );
}
}
