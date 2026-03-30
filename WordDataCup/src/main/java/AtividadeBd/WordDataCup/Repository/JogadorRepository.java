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

<<<<<<< HEAD
        String sql = "INSERT INTO Jogador (numero_camisa, inscricao, posicao, selecao_id, nome, idade) VALUES (?, ?, ?, ?, ?, ?)";
=======
        String sql = "INSERT INTO Jogador (numero_camisa, inscricao, posicao, selecao_id, nome, idade, fK_selecao_inscricao, capitao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
>>>>>>> 5d07769 (Concertando)

        jdbcTemplate.update(sql,
                jogador.getNumeroCamisa(),
                jogador.getInscricao(),
                jogador.getPosicao(),
                jogador.getSelecao(),
                jogador.getNome(),
<<<<<<< HEAD
                jogador.getIdade()
=======
                jogador.getIdade(),
                jogador.getFkSelecaoInscricao(),
                jogador.getCapitao()
>>>>>>> 5d07769 (Concertando)
        );

        System.out.println("Inserção feita");
    }

    public void atualizarJogador(Jogador jogador) {
<<<<<<< HEAD
        String sql = "UPDATE Jogador SET numero_camisa = ?, posicao = ?, selecao_id = ?, nome= ?, idade = ? WHERE inscricao = ?";
=======
        String sql = "UPDATE Jogador SET numero_camisa = ?, posicao = ?, selecao_id = ?, nome= ?, idade = ?, fk_selecao_inscricao= ?, capitao = ?  WHERE inscricao = ?";
>>>>>>> 5d07769 (Concertando)

        jdbcTemplate.update(sql,
                jogador.getNumeroCamisa(),
                jogador.getPosicao(),
                jogador.getSelecao(),
                jogador.getNome(),
                jogador.getIdade(),
<<<<<<< HEAD
                jogador.getInscricao()
=======
                jogador.getInscricao(),
                jogador.getFkSelecaoInscricao(),
                jogador.getCapitao()
>>>>>>> 5d07769 (Concertando)
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