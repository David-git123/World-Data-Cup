package AtividadeBd.WordDataCup.Repository;

import AtividadeBd.WordDataCup.Model.Grupo;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GrupoRepository {
    private final JdbcTemplate jdbcTemplate;

    public GrupoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void inserirGrupo(Grupo grupo){
        String sql = "INSERT INTO GRUPO (letra_identificadora,lugar1,lugar2,lugar3,lugar4) VALUES(?,?,?,?,?)";

        jdbcTemplate.update(sql,
                grupo.getLetraIdentificadora(),
                grupo.getLugar1(),
                grupo.getLugar2(),
                grupo.getLugar3(),
                grupo.getLugar4()
        );
    }

    public void atualizarGrupo(Grupo grupo){
        String sql = "UPDATE GRUPO SET lugar1=?, lugar2 = ?, lugar3 = ?, lugar4 =? WHERE letra_identificadora = ? ";

        jdbcTemplate.update(sql,
                grupo.getLugar1(),
                grupo.getLugar2(),
                grupo.getLugar3(),
                grupo.getLugar4(),
                grupo.getLetraIdentificadora()
        );
    }

    public void deletarGrupo(Grupo grupo){
        String sql = "DELETE from grupo WHERE letra_identificadora = ?";

        jdbcTemplate.update(sql,
                grupo.getLetraIdentificadora()
        );
    }

        public List<Grupo> listarTodos(){

        String sql = "SELECT * FROM grupo";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Grupo(
                        rs.getString("letra_identificadora").charAt(0),
                        rs.getString("lugar1"),
                        rs.getString("lugar2"),
                        rs.getString("lugar3"),
                        rs.getString("lugar4")
                )
        );
    }
}
