package AtividadeBd.WordDataCup.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstatisticaRepository {

    private final JdbcTemplate jdbcTemplate;

    public EstatisticaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int [] retornarTodasQuantidades(){
        String[] arrayNomeTabelas = {"selecao","jogador","jogo","treinador","preparador_fisico","auxiliar_tecnico","comissao_tecnica","estadio","grupo","mascote",
                "pais_sede"};
        int[] arrayResultados = new int[11];
        for (int i=0;i<11;i++){
            String sql = "SELECT COUNT(*) FROM "+arrayNomeTabelas[i];
            arrayResultados[i] = jdbcTemplate.queryForObject(sql,Integer.class);
        }
        return arrayResultados;
    }

    public List<Integer> retornarMediaGolsSelecao(){

        String sql = "SELECT media_gols FROM selecao";

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> rs.getInt("media_gols")
        );

    }

    public List<Integer> retornarMediaIdadeSelecao(){
        String sql = "SELECT media_idade FROM selecao";

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> rs.getInt("media_idade")
        );
    }

    public List<Object[]> retornarComissaoTecnicaNacionalidade(){
        String sql = "SELECT nacionalidade, COUNT(*) FROM comissao_tecnica GROUP BY nacionalidade";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Object[]{
                        rs.getString("nacionalidade"),
                        rs.getInt("total")
                }
        );

    }

    public List<Object[]> retornarRankingNome(){
        String sql = "SELECT nome,ranking_da_fifa FROM selecao";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Object[]{
                        rs.getString("nome"),
                        rs.getInt("ranking_da_fifa")
                }
        );
    }

    public List<Object[]> retornarCategoriaEstadios(){

        String sql = """
            SELECT nome,
                capacidade_maxima,
                fn_categoria_estadio(id) AS categoria
            FROM estadio
        """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Object[]{
                        rs.getString("nome"),
                        rs.getInt("capacidade_maxima"),
                        rs.getString("categoria")
                }
        );
    }

    public List<Object[]> listarLogs(){

        String sql = "SELECT * FROM logs_auditoria";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Object[]{
                        rs.getInt("id"),
                        rs.getString("tabela_nome"),
                        rs.getString("acao"),
                        rs.getString("valor_antigo"),
                        rs.getString("valor_novo"),
                        rs.getTimestamp("data_hora")
                }
        );
    }

    public List<Object[]> mediaIdadeContinente(){

        String sql = """
            SELECT s.continente,
                AVG(j.idade) AS media_geral_idade
            FROM selecao s
            JOIN jogador j
            ON s.inscricao = j.fk_selecao_inscricao
            GROUP BY s.continente
            HAVING AVG(j.idade) > 25
        """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Object[]{
                        rs.getString("continente"),
                        rs.getDouble("media_geral_idade")
                }
        );
    }
}
