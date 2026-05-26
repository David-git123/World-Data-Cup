package AtividadeBd.WordDataCup.Repository;

import AtividadeBd.WordDataCup.Model.Grupo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GrupoRepository {
    private final JdbcTemplate jdbcTemplate;

    public GrupoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void inserirGrupo(Grupo grupo){
        String sql = "INSERT INTO GRUPO (letra_identificadora,lugar1,lugar2,lugar3,lugar4) VALUES(?,?,?,?)";

        jdbcTemplate.update(sql,
                grupo.getLetraIdentificadora(),
                grupo.getLugar1(),
                grupo.getLugar2(),
                grupo.getLugar3(),
                grupo.getLugar4()
        );
    }

    public void atualizarGrupo(Grupo grupo){
        String sql = "UPDATE GRUPO SET lugar1=?, lugar2 = ?, lugar3 = ?, lugar 4 =? WHERE letra_identificadora = ? ";

        jdbcTemplate.update(sql,
                grupo.getLugar1(),
                grupo.getLugar2(),
                grupo.getLugar3(),
                grupo.getLugar4(),
                grupo.getLetraIdentificadora()
        );
    }

    public void deletarGrupo(Grupo grupo){
        String sql = "DELETE from GRUPO letra_identificadora = ?";

        jdbcTemplate.update(sql,
                grupo.getLetraIdentificadora()
        );
    }
}
