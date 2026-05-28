package AtividadeBd.WordDataCup.Controller;

import AtividadeBd.WordDataCup.Model.*;
import AtividadeBd.WordDataCup.Repository.*;
import AtividadeBd.WordDataCup.Service.ServiceEntidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
public class Controlador {

    @Autowired JogadorRepository jogadorRepository;
    @Autowired SelecaoRepository selecaoRepository;
    @Autowired AuxiliarTecnicoRepository auxiliarTecnicoRepository;
    @Autowired ComissaoTecnicaRepository comissaoTecnicaRepository;
    @Autowired EstadioRepository estadioRepository;
    @Autowired GrupoRepository grupoRepository;
    @Autowired EstatisticaRepository estatisticaRepository;
    @Autowired MascoteRepository mascoteRepository;
    @Autowired PaisSedeRepository paisSedeRepository;
    @Autowired PreparadorFisicoRepository preparadorFisicoRepository;
    @Autowired TreinadorRepository treinadorRepository;
    @Autowired JogoRepository jogoRepository;
    @Autowired JdbcTemplate jdbcTemplate;

/* ══════════════════════════════════════════
PÁGINA PRINCIPAL
══════════════════════════════════════════ */

    @GetMapping("/")
    public String retornarPagina() {
        return "home";
    }

/* ══════════════════════════════════════════
FUNÇÕES SQL
══════════════════════════════════════════ */

    @GetMapping("/api/fn/categoria-estadio")
    @ResponseBody
    public Map<String, Object> categoriaEstadio(@RequestParam int id) {
        String sql = "SELECT id, nome, capacidade_maxima, fn_categoria_estadio(id) AS categoria FROM estadio WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @GetMapping("/api/fn/categoria-estadios-todos")
    @ResponseBody
    public List<Map<String, Object>> categoriaEstadiosTodos() {
        String sql = "SELECT id, nome, capacidade_maxima, fn_categoria_estadio(id) AS categoria FROM estadio ORDER BY capacidade_maxima DESC";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/api/fn/maturidade-selecao")
    @ResponseBody
    public Map<String, Object> maturidadeSelecao(@RequestParam int id) {
        String sql = "SELECT inscricao, nome, media_idade, categorizar_maturidade_selecao(inscricao) AS maturidade FROM selecao WHERE inscricao = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @GetMapping("/api/fn/maturidade-selecoes-todas")
    @ResponseBody
    public List<Map<String, Object>> maturidadeSelecoesTodas() {
        String sql = "SELECT inscricao, nome, media_idade, categorizar_maturidade_selecao(inscricao) AS maturidade FROM selecao ORDER BY media_idade DESC";
        return jdbcTemplate.queryForList(sql);
    }

/* ══════════════════════════════════════════
TRIGGERS — CARTÕES E SUSPENSÕES
══════════════════════════════════════════ */

    @GetMapping("/api/cartoes")
    @ResponseBody
    public Map<String, Object> registrarCartao(
            @RequestParam int jogador,
            @RequestParam int jogo,
            @RequestParam String tipo,
            @RequestParam int minuto) {
        jdbcTemplate.update(
                "INSERT INTO cartoes (fkjogadorinscricao, fkjogoid, tipo_cartao, minuto) VALUES (?, ?, ?, ?)",
                jogador, jogo, tipo, minuto
        );
        Map<String, Object> resp = new java.util.HashMap<>();
        resp.put("status", "ok");
        resp.put("tipo", tipo);
        resp.put("jogador", jogador);
        resp.put("jogo", jogo);
        resp.put("minuto", minuto);
        return resp;
    }

    @GetMapping("/api/suspensoes")
    @ResponseBody
    public List<Map<String, Object>> listarSuspensoes() {
        String sql = "SELECT id, fkjogadorinscricao, jogos_a_cumprir, motivo, data_registro AS dataRegistro FROM suspensoes ORDER BY data_registro DESC";
        return jdbcTemplate.queryForList(sql);
    }

/* ══════════════════════════════════════════
CONSULTAS SQL PERSONALIZADAS
══════════════════════════════════════════ */

    @GetMapping("/api/selecoes-sem-jogadores")
    @ResponseBody
    public List<Map<String, Object>> selecoesSemJogadores() {
        String sql = """
SELECT s.inscricao, s.nome, s.continente,
s.fk_grupo_letra_identificadora AS fk_grupo_letra_identificacao
FROM selecao s
LEFT JOIN jogador j ON s.inscricao = j.fk_selecao_inscricao
WHERE j.inscricao IS NULL
ORDER BY s.nome
""";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/api/jogadores-mascote")
    @ResponseBody
    public List<Object[]> jogadoresMascote(@RequestParam String selecao) {
        String sql = """
SELECT j.nome AS Nome_Jogador, s.nome AS Nome_Selecao, m.nome AS Nome_Mascote
FROM jogador j
JOIN selecao s ON j.fk_selecao_inscricao = s.inscricao
JOIN mascote m ON s.inscricao = m.fk_selecao_inscricao
WHERE s.nome = ?
ORDER BY j.nome
""";
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new Object[]{
                        rs.getString("Nome_Jogador"),
                        rs.getString("Nome_Selecao"),
                        rs.getString("Nome_Mascote")
                }, selecao);
    }

    @GetMapping("/api/jogos-destaque")
    @ResponseBody
    public List<Map<String, Object>> jogosDestaque(
            @RequestParam(defaultValue = "5") int pontuacao) {
        String sql = """
SELECT id, placar, vencedor
FROM jogo
WHERE vencedor IN (SELECT nome FROM selecao WHERE pontuacao > ?)
ORDER BY id
""";
        return jdbcTemplate.queryForList(sql, pontuacao);
    }

    @GetMapping("/estatisticas/continentes")
    @ResponseBody
    public List<Object[]> mediaIdadeContinente(
            @RequestParam(defaultValue = "25") double minMedia) {
        String sql = """
SELECT s.continente, AVG(j.idade) AS media_geral_idade
FROM selecao s
JOIN jogador j ON s.inscricao = j.fk_selecao_inscricao
GROUP BY s.continente
HAVING AVG(j.idade) > ?
ORDER BY media_geral_idade DESC
""";
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new Object[]{
                        rs.getString("continente"),
                        rs.getDouble("media_geral_idade")
                }, minMedia);
    }

    @GetMapping("/estatisticas/logs")
    @ResponseBody
    public List<Object[]> listarLogs() {
        String sql = "SELECT * FROM logs_auditoria ORDER BY data_hora DESC";
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new Object[]{
                        rs.getInt("id"),
                        rs.getString("tabela_nome"),
                        rs.getString("acao"),
                        rs.getString("chave_registro"),
                        rs.getString("valor_antigo"),
                        rs.getString("valor_novo"),
                        rs.getString("usuario"),
                        rs.getTimestamp("data_hora")
                });
    }

/* ══════════════════════════════════════════
OPERAÇÕES CRUD — JOGADOR
══════════════════════════════════════════ */

    @PostMapping("/jogadores")
    public String operacaoJogadores(
            @RequestParam("operacao") String operacao,
            @RequestParam("n_camisa") int camisa,
            @RequestParam("n_inscricao") int inscricao,
            @RequestParam("posicao") String posicao,
            @RequestParam("nome_atleta") String nome_atleta,
            @RequestParam("idade") int idade,
            @RequestParam("fk_selecao_inscricao") int fk_selecao_inscricao,
            @RequestParam("capitao") int capitao,
            Model model) {

        Jogador jogador = new Jogador(camisa, inscricao, posicao, nome_atleta, idade, fk_selecao_inscricao, capitao);
        if (operacao.equals("Inserir")) jogadorRepository.inserirJogador(jogador);
        if (operacao.equals("Alterar")) jogadorRepository.atualizarJogador(jogador);
        if (operacao.equals("Deletar")) jogadorRepository.deletarJogador(jogador);
        return "redirect:/";
    }

/* ══════════════════════════════════════════
OPERAÇÕES CRUD — SELEÇÃO
══════════════════════════════════════════ */

    @PostMapping("/selecao")
    public String operacaoSelecao(
            @RequestParam("operacao") String operacao,
            @RequestParam("n_inscricao") int inscricao,
            @RequestParam("mediaGols") int mediaGols,
            @RequestParam("rankingFifa") int rankingFifa,
            @RequestParam("mediaIdade") int mediaIdade,
            @RequestParam("fk_grupo_letra_identificadora") String fk_letra_identificadora,
            @RequestParam("pontuacao") int pontuacao,
            @RequestParam("nome") String nome,
            @RequestParam("continente") String continente,
            Model model) {

        Selecao selecao = new Selecao(inscricao, mediaIdade, mediaGols, rankingFifa, continente, pontuacao, nome, fk_letra_identificadora);
        if (operacao.equals("Inserir")) selecaoRepository.inserirSelecao(selecao);
        if (operacao.equals("Alterar")) selecaoRepository.atualizarSelecao(selecao);
        if (operacao.equals("Deletar")) selecaoRepository.deletarSelecao(selecao);
        return "redirect:/";
    }

/* ══════════════════════════════════════════
OPERAÇÕES CRUD — TREINADOR
══════════════════════════════════════════ */

    @PostMapping("/treinador")
    public String operacaoTreinador(
            @RequestParam("operacao") String operacao,
            @RequestParam("fkComissaoTecnicaInscricao") int fkComissaoTecnicaInscricao,
            @RequestParam("estiloJogo") String estiloJogo,
            @RequestParam("esquemaTaticoPref") String esquemaTaticoPref,
            @RequestParam("metodologiaTreino") String metodologiaTreino) {

        Treinador treinador = new Treinador(fkComissaoTecnicaInscricao, estiloJogo, esquemaTaticoPref, metodologiaTreino);
        if (operacao.equals("Inserir")) treinadorRepository.inserirTreinador(treinador);
        if (operacao.equals("Alterar")) treinadorRepository.atualizarTreinador(treinador);
        if (operacao.equals("Deletar")) treinadorRepository.deletarTreinador(treinador);
        return "redirect:/";
    }

/* ══════════════════════════════════════════
OPERAÇÕES CRUD — PREPARADOR FÍSICO
══════════════════════════════════════════ */

    @PostMapping("/preparador")
    public String operacaoPreparadorFisico(
            @RequestParam("operacao") String operacao,
            @RequestParam("fkComissaoTecnicaInscricao") int fkComissaoTecnicaInscricao,
            @RequestParam("especializacao") String especializacao,
            @RequestParam("metodologiaTreino") String metodologiaTreino) {

        PreparadorFisico preparador = new PreparadorFisico(fkComissaoTecnicaInscricao, especializacao, metodologiaTreino);
        if (operacao.equals("Inserir")) preparadorFisicoRepository.inserirPreparadorFisico(preparador);
        if (operacao.equals("Alterar")) preparadorFisicoRepository.atualizarPreparadorFisico(preparador);
        if (operacao.equals("Deletar")) preparadorFisicoRepository.deletarPreparadorFisico(preparador);
        return "redirect:/";
    }

/* ══════════════════════════════════════════
OPERAÇÕES CRUD — PAÍS SEDE
══════════════════════════════════════════ */

    @PostMapping("/pais")
    public String operacaoPaisSede(
            @RequestParam("operacao") String operacao,
            @RequestParam("nomePais") String nomePais,
            @RequestParam("numeroJogosDoPais") int numeroJogosDoPais,
            @RequestParam("numeroEstadios") int numeroEstadios,
            @RequestParam("nomeMascote") String nomeMascote) {

        PaisSede pais = new PaisSede(nomePais, numeroJogosDoPais, numeroEstadios, nomeMascote);
        if (operacao.equals("Inserir")) paisSedeRepository.inserirPaisSede(pais);
        if (operacao.equals("Alterar")) paisSedeRepository.atualizarPaisSede(pais);
        if (operacao.equals("Deletar")) paisSedeRepository.deletarPaisSede(pais);
        return "redirect:/";
    }

/* ══════════════════════════════════════════
OPERAÇÕES CRUD — MASCOTE
══════════════════════════════════════════ */

    @PostMapping("/mascote")
    public String operacaoMascote(
            @RequestParam("operacao") String operacao,
            @RequestParam("nome") String nome,
            @RequestParam("id") int id,
            @RequestParam("fkInscricaoSelecao") int fkInscricaoSelecao) {

        Mascote mascote = new Mascote(nome, id, fkInscricaoSelecao);
        if (operacao.equals("Inserir")) mascoteRepository.inserirMascote(mascote);
        if (operacao.equals("Alterar")) mascoteRepository.atualizarMascote(mascote);
        if (operacao.equals("Deletar")) mascoteRepository.deletarMascote(mascote);
        return "redirect:/";
    }

/* ══════════════════════════════════════════
OPERAÇÕES CRUD — JOGO
══════════════════════════════════════════ */

    @PostMapping("/jogo")
    public String operacaoJogo(
            @RequestParam("operacao") String operacao,
            @RequestParam("nomePais") int id,
            @RequestParam("placar") String placar,
            @RequestParam("data") LocalDateTime data,
            @RequestParam("vencedor") String vencedor,
            @RequestParam("perdedor") String perdedor,
            @RequestParam("tipoJogo") String tipoJogo) {

        Jogo jogo = new Jogo(id, data, placar, vencedor, perdedor, tipoJogo);
        if (operacao.equals("Inserir")) jogoRepository.inserirJogo(jogo);
        if (operacao.equals("Alterar")) jogoRepository.atualizarJogo(jogo);
        if (operacao.equals("Deletar")) jogoRepository.deletarJogo(jogo);
        return "redirect:/";
    }

/* ══════════════════════════════════════════
OPERAÇÕES CRUD — ESTÁDIO
══════════════════════════════════════════ */

    @PostMapping("/estadio")
    public String operacaoEstadio(
            @RequestParam("operacao") String operacao,
            @RequestParam("id") int id,
            @RequestParam("cidade") String cidade,
            @RequestParam("capacidadeMaxima") int capacidadeMaxima,
            @RequestParam("nome") String nome,
            @RequestParam("fkPaisSedeNomePais") String fkPaisSedeNomePais) {

        Estadio estadio = new Estadio(id, cidade, capacidadeMaxima, nome, fkPaisSedeNomePais);
        if (operacao.equals("Inserir")) estadioRepository.inserirEstadio(estadio);
        if (operacao.equals("Alterar")) estadioRepository.atualizarEstadio(estadio);
        if (operacao.equals("Deletar")) estadioRepository.deletarEstadio(estadio);
        return "redirect:/";
    }

/* ══════════════════════════════════════════
OPERAÇÕES CRUD — COMISSÃO TÉCNICA
══════════════════════════════════════════ */

    @PostMapping("/comissao")
    public String operacaoComissaoTecnica(
            @RequestParam("operacao") String operacao,
            @RequestParam("nome") String nome,
            @RequestParam("nacionalidade") String nacionalidade,
            @RequestParam("inscricao") int inscricao,
            @RequestParam("fkInscricaoSelecao") int fkInscricaoSelecao) {

        ComissaoTecnica comissao = new ComissaoTecnica(nome, nacionalidade, inscricao, fkInscricaoSelecao);
        if (operacao.equals("Inserir")) comissaoTecnicaRepository.inserirComissaoTecnica(comissao);
        if (operacao.equals("Alterar")) comissaoTecnicaRepository.atualizarComissaoTecnica(comissao);
        if (operacao.equals("Deletar")) comissaoTecnicaRepository.deletarComissaoTecnica(comissao);
        return "redirect:/";
    }

/* ══════════════════════════════════════════
OPERAÇÕES CRUD — AUXILIAR TÉCNICO
══════════════════════════════════════════ */

    @PostMapping("/auxiliar")
    public String operacaoAuxiliarTecnico(
            @RequestParam("operacao") String operacao,
            @RequestParam("fkComissaoTecnicaInscricao") int fkComissaoTecnicaInscricao,
            @RequestParam("especialidade") String especialidade,
            @RequestParam("focoSetorial") String focoSetorial) {

        AuxiliarTecnico auxiliarTecnico = new AuxiliarTecnico(fkComissaoTecnicaInscricao, especialidade, focoSetorial);
        if (operacao.equals("Inserir")) auxiliarTecnicoRepository.inserirAuxiliarTecnico(auxiliarTecnico);
        if (operacao.equals("Alterar")) auxiliarTecnicoRepository.atualizarAuxiliarTecnico(auxiliarTecnico);
        if (operacao.equals("Deletar")) auxiliarTecnicoRepository.deletarAuxiliarTecnico(auxiliarTecnico);
        return "redirect:/";
    }

/* ══════════════════════════════════════════
OPERAÇÕES CRUD — GRUPO
══════════════════════════════════════════ */

    @PostMapping("/grupo")
    public String operacaoGrupo(
            @RequestParam("operacao") String operacao,
            @RequestParam("letraIdentificadora") char letraIdentificadora,
            @RequestParam("lugar1") String lugar1,
            @RequestParam("lugar2") String lugar2,
            @RequestParam("lugar3") String lugar3,
            @RequestParam("Lugar4") String lugar4) {

        Grupo grupo = new Grupo(letraIdentificadora, lugar1, lugar2, lugar3, lugar4);
        if (operacao.equals("Inserir")) grupoRepository.inserirGrupo(grupo);
        if (operacao.equals("Alterar")) grupoRepository.atualizarGrupo(grupo);
        if (operacao.equals("Deletar")) grupoRepository.deletarGrupo(grupo);
        return "redirect:/";
    }

/* ══════════════════════════════════════════
API REST — CONSULTA (usada pela seção
"05 — Consultar Dados" do home.html)
══════════════════════════════════════════ */

    @GetMapping("/api/jogadores")
    @ResponseBody
    public List<Jogador> listarJogadores() {
        return jogadorRepository.listarTodos();
    }

    @GetMapping("/api/selecoes")
    @ResponseBody
    public List<Selecao> listarSelecoes() {
        return selecaoRepository.listarTodos();
    }

    @GetMapping("/api/grupos")
    @ResponseBody
    public List<Grupo> listarGrupos() {
        return grupoRepository.listarTodos();
    }

    @GetMapping("/api/jogos")
    @ResponseBody
    public List<Jogo> listarJogos() {
        return jogoRepository.listarTodos();
    }

    @GetMapping("/api/estadios")
    @ResponseBody
    public List<Estadio> listarEstadios() {
        return estadioRepository.listarTodos();
    }

    @GetMapping("/api/paises")
    @ResponseBody
    public List<PaisSede> listarPaises() {
        return paisSedeRepository.listarTodos();
    }

    @GetMapping("/api/mascotes")
    @ResponseBody
    public List<Mascote> listarMascotes() {
        return mascoteRepository.listarTodos();
    }

    @GetMapping("/api/comissoes")
    @ResponseBody
    public List<ComissaoTecnica> listarComissoes() {
        return comissaoTecnicaRepository.listarTodos();
    }

    @GetMapping("/api/treinadores")
    @ResponseBody
    public List<Treinador> listarTreinadores() {
        return treinadorRepository.listarTodos();
    }

    @GetMapping("/api/preparadores")
    @ResponseBody
    public List<PreparadorFisico> listarPreparadores() {
        return preparadorFisicoRepository.listarTodos();
    }

    @GetMapping("/api/auxiliares")
    @ResponseBody
    public List<AuxiliarTecnico> listarAuxiliares() {
        return auxiliarTecnicoRepository.consultaTotalAuxiliarTecnico();
    }
}

