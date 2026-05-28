package AtividadeBd.WordDataCup.Repository;

import AtividadeBd.WordDataCup.Model.ComissaoTecnica;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ComissaoTecnicaRepository {
    private final JdbcTemplate jdbcTemplate;

    public ComissaoTecnicaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserirComissaoTecnica(ComissaoTecnica comissaoTecnica){
        String sql = "INSERT INTO comissao_tecnica (nome,nacionalidade,inscricao,fk_inscricao_selecao) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql,
                comissaoTecnica.getNome(),
                comissaoTecnica.getNacionalidade(),
                comissaoTecnica.getInscricao(),
                comissaoTecnica.getFkInscricaoSelecao()
        );
    }

    public void atualizarComissaoTecnica(ComissaoTecnica comissaoTecnica){
        String sql = "UPDATE comissao_tecnica SET nome = ?, nacionalidade = ?, fk_inscricao_selecao = ? WHERE inscricao = ?";
        jdbcTemplate.update(sql,
                comissaoTecnica.getNome(),
                comissaoTecnica.getNacionalidade(),
                comissaoTecnica.getFkInscricaoSelecao(),
                comissaoTecnica.getInscricao()
        );
    }

    public void deletarComissaoTecnica(ComissaoTecnica comissaoTecnica){
        String sql = "DELETE FROM comissao_tecnica WHERE inscricao = ?";
        jdbcTemplate.update(sql,
                comissaoTecnica.getInscricao()
        );
    }
    
    public List<ComissaoTecnica> listarTodos(){

        String sql = "SELECT * FROM comissao_tecnica";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new ComissaoTecnica(
                        rs.getString("nome"),
                        rs.getString("nacionalidade"),
                        rs.getInt("inscricao"),
                        rs.getInt("fk_inscricao_selecao")
                )
        );
    }

}
