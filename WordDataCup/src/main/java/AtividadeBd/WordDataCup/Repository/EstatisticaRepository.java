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




}
