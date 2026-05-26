package AtividadeBd.WordDataCup.Repository;

import AtividadeBd.WordDataCup.Model.PaisSede;
import org.springframework.jdbc.core.JdbcTemplate;

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
        String sql= "UPDATE pais_sede SET numero_jogo_do_pais = ?, numero_estadios = ?, nome_mascote = ? WHERE pais_sede = ?";
        jdbcTemplate.update(sql,
                paisSede.getNumeroJogosDopais(),
                paisSede.getNumeroEstadios(),
                paisSede.getNomeMascote(),
                paisSede.getNomePais()
        );
    }

    public void deletarPaisSede(PaisSede paisSede){
        String sql = "DELETE FROM pais_sede WHERE = nome_pais = ?";
        jdbcTemplate.update(sql,
                paisSede.getNomePais()
        );
    }
}
