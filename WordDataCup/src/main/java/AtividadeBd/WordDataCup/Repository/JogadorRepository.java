package AtividadeBd.WordDataCup.Repository;

import AtividadeBd.WordDataCup.Model.Jogador;
import AtividadeBd.WordDataCup.Util.ConnectionFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Essa anotação é para tratar a classe como bean do spring boot e tratar exeções SQL( Se não for permitido tiro)
@Repository
public class JogadorRepository {
    //Aprender isso.
    public void inserir( Jogador jogador) throws SQLException {
        String sql = "INSERT INTO JOGADOR (numeroCamisa, inscricao, posicao, selecao,nome,idade) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, jogador.getNumeroCamisa());
            ps.setInt(2, jogador.getInscricao());
            ps.setString(3, jogador.getPosicao());
            ps.setString(4, jogador.getSelecao());
            ps.setString(5, jogador.getNome());
            ps.setInt(6, jogador.getIdade());
            ps.executeUpdate();
        }
    }
}

