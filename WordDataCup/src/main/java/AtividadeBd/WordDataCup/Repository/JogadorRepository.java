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

        String sql = "INSERT INTO jogador (numero_da_camisa, inscricao, posicao, nome, idade, fK_selecao_inscricao, capitao) VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                jogador.getNumeroCamisa(),
                jogador.getInscricao(),
                jogador.getPosicao(),
                jogador.getNome(),
                jogador.getIdade(),
                jogador.getFkSelecaoInscricao(),
                jogador.getCapitao()
        );

        System.out.println("Inserção feita");
    }

    public void atualizarJogador(Jogador jogador) {
        String sql = "UPDATE jogador SET numero_da_camisa = ?, posicao = ?,nome= ?, idade = ?, fk_selecao_inscricao= ?, capitao = ?  WHERE inscricao = ?";

        jdbcTemplate.update(sql,
                jogador.getNumeroCamisa(),
                jogador.getPosicao(),
                jogador.getNome(),
                jogador.getIdade(),
                jogador.getInscricao(),
                jogador.getFkSelecaoInscricao(),
                jogador.getCapitao()
        );

        System.out.println("Atualização feita");
    }

    public void deletarJogador(Jogador jogador) {
        String sql = "DELETE FROM jogador WHERE inscricao = ?";

        jdbcTemplate.update(sql,
                jogador.getInscricao()
        );

        System.out.println("Exclusão feita");
    }

}