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

    public void inserirJogador(Jogador jogador) {

        String sql = "INSERT INTO Jogador (numero_camisa, inscricao, posicao, selecao_id, nome, idade) VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                jogador.getNumeroCamisa(),
                jogador.getInscricao(),
                jogador.getPosicao(),
                jogador.getSelecao(),
                jogador.getNome(),
                jogador.getIdade()
        );

        System.out.println("Inserção feita");
    }

    public void atualizarJogador(Jogador jogador) {
        String sql = "UPDATE Jogador SET numero_camisa = ?, posicao = ?, selecao_id = ?, nome= ?, idade = ? WHERE inscricao = ?";

        jdbcTemplate.update(sql,
                jogador.getNumeroCamisa(),
                jogador.getPosicao(),
                jogador.getSelecao(),
                jogador.getNome(),
                jogador.getIdade(),
                jogador.getInscricao()
        );

        System.out.println("Atualização feita");
    }

    public void deletarJogador(Jogador jogador) {
        String sql = "DELETE FROM Jogador WHERE inscricao = ?";

        jdbcTemplate.update(sql,
                jogador.getInscricao()
        );

        System.out.println("Exclusão feita");
    }

}