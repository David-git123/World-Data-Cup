package AtividadeBd.WordDataCup.Repository;

import AtividadeBd.WordDataCup.Model.Jogador;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JogadorRepository {

    private final JdbcTemplate jdbcTemplate;

    public JogadorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserir(Jogador jogador) {

        String sql = "INSERT INTO Jogador (numero_camisa, inscricao, posicao, selecao_id, nome, idade) VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                jogador.getNumeroCamisa(),
                jogador.getInscricao(),
                jogador.getPosicao(),
                jogador.getSelecao(),
                jogador.getNome(),
                jogador.getIdade()
        );

        System.out.println("Inserção feita com JdbcTemplate");
    }
}