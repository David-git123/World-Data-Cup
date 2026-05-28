package AtividadeBd.WordDataCup.Repository;

import AtividadeBd.WordDataCup.Model.PaisSede;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PaisSedeRepository {
    private final JdbcTemplate jdbcTemplate;

    public PaisSedeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserirPaisSede(PaisSede paisSede){
        String sql = "INSERT INTO pais_sede (nome_pais,numero_jogos_do_pais,numero_estadios,nome_mascote) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql,
                paisSede.getNomePais(),
                paisSede.getNumeroJogosDopais(),
                paisSede.getNumeroEstadios(),
                paisSede.getNomeMascote()
        );
    }
    public void atualizarPaisSede(PaisSede paisSede){
        String sql= "UPDATE pais_sede SET numero_jogos_do_pais = ?, numero_estadios = ?, nome_mascote = ? WHERE nome_pais = ?";
        jdbcTemplate.update(sql,
                paisSede.getNumeroJogosDopais(),
                paisSede.getNumeroEstadios(),
                paisSede.getNomeMascote(),
                paisSede.getNomePais()
        );
    }

    public void deletarPaisSede(PaisSede paisSede){
        String sql = "DELETE FROM pais_sede WHERE nome_pais = ?";
        jdbcTemplate.update(sql,
                paisSede.getNomePais()
        );
    }

    public List<PaisSede> listarTodos(){
        String sql = "SELECT * FROM pais_sede";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PaisSede paisSede = new PaisSede();
            
            paisSede.setNomePais(rs.getString("nome_pais"));
            paisSede.setNumeroJogosDopais(rs.getInt("numero_jogos_do_pais"));
            paisSede.setNumeroEstadios(rs.getInt("numero_estadios"));
            paisSede.setNomeMascote(rs.getString("nome_mascote"));
            
            return paisSede;
        });
    }
}
