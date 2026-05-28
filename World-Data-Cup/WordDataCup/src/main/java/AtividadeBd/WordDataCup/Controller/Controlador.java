package AtividadeBd.WordDataCup.Controller;

import AtividadeBd.WordDataCup.Model.*;
import AtividadeBd.WordDataCup.Repository.*;
import AtividadeBd.WordDataCup.Service.ServiceEntidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class Controlador{

    @Autowired
    JogadorRepository jogadorRepository;
    @Autowired
    SelecaoRepository selecaoRepository;
    @Autowired
    AuxiliarTecnicoRepository auxiliarTecnicoRepository;
    @Autowired
    ComissaoTecnicaRepository comissaoTecnicaRepository;
    @Autowired
    EstadioRepository estadioRepository;
    @Autowired
    GrupoRepository grupoRepository;
    @Autowired
    EstatisticaRepository estatisticaRepository;
    @Autowired
    MascoteRepository mascoteRepository;
    @Autowired
    PaisSedeRepository paisSedeRepository;
    @Autowired
    PreparadorFisicoRepository preparadorFisicoRepository;
    @Autowired
    TreinadorRepository treinadorRepository;
    @Autowired
    JogoRepository jogoRepository;

    @GetMapping("/")
    public String retornarPagina(){

        return "home";
    }

    @PostMapping("/jogadores")

    public String operacaoJogadores(@RequestParam("operacao") String operacao, @RequestParam("n_camisa") int camisa,@RequestParam("n_inscricao") int inscricao,
                                    @RequestParam("posicao") String posicao,@RequestParam("nome_atleta") String nome_atleta,
                                    @RequestParam("idade") int idade, @RequestParam("fk_selecao_inscricao") int fk_selecao_inscricao, @RequestParam("capitao") int capitao,
                                    Model model){

        Jogador jogador = new Jogador(camisa,inscricao,posicao,nome_atleta,idade,fk_selecao_inscricao,capitao);
        if(operacao.equals("Inserir")){
            jogadorRepository.inserirJogador((jogador));
        }
        if(operacao.equals("Alterar")){
            jogadorRepository.atualizarJogador((jogador));
        }
        if(operacao.equals("Deletar")){
            jogadorRepository.deletarJogador((jogador));
        }
        return "redirect:/";

    }

    @PostMapping("/selecao")
    public String operacaoSelecao(@RequestParam("operacao") String operacao, @RequestParam("n_inscricao") int inscricao,
                                  @RequestParam("mediaGols") int mediaGols,@RequestParam("rankingFifa") int rankingFifa,
                                  @RequestParam("mediaIdade") int mediaIdade,@RequestParam("fk_grupo_letra_identificadora") String fk_letra_identificadora,
                                  @RequestParam("pontuacao") int pontuacao, @RequestParam("nome") String nome,@RequestParam("continente") String continente,
                                  Model model){
        Selecao selecao = new Selecao(inscricao,mediaIdade,mediaGols,rankingFifa ,continente,pontuacao,nome,fk_letra_identificadora);

        if(operacao.equals("Inserir")){
            selecaoRepository.inserirSelecao((selecao));
        }
        if(operacao.equals("Alterar")){
            selecaoRepository.atualizarSelecao((selecao));
        }
        if(operacao.equals("Deletar")){
            selecaoRepository.deletarSelecao((selecao));
        }
        return "redirect:/";
    }
    @PostMapping("/treinador")
    public String operacaoTreinador(@RequestParam("operacao") String operacao,@RequestParam("fkComissaoTecnicaInscricao") int fkComissaoTecnicaInscricao,@RequestParam("estiloJogo")String estiloJogo,
                                    @RequestParam("esquemaTaticoPref") String esquemaTaticoPref,@RequestParam("metodologiaTreino")String metodologiaTreino){

        Treinador treinador = new Treinador(fkComissaoTecnicaInscricao,estiloJogo,esquemaTaticoPref,metodologiaTreino);
        if(operacao.equals("Inserir")){

            treinadorRepository.inserirTreinador(treinador);
        }
        if(operacao.equals("Alterar")){
            treinadorRepository.atualizarTreinador(treinador);
        }
        if(operacao.equals("Deletar")){
            treinadorRepository.deletarTreinador(treinador);
        }
        return "redirect:/";

    }

    @PostMapping("/preparador")
    public String operacaoPreparadorFisico(@RequestParam("operacao") String operacao,@RequestParam("fkComissaoTecnicaInscricao") int fkComissaoTecnicaInscricao,
                                           @RequestParam("especializacao")String especializacao,@RequestParam("metodologiaTreino")String metodologiaTreino){
        PreparadorFisico preparador = new PreparadorFisico(fkComissaoTecnicaInscricao,especializacao,metodologiaTreino);
        if(operacao.equals("Inserir")){

            preparadorFisicoRepository.inserirPreparadorFisico(preparador);
        }
        if(operacao.equals("Alterar")){
            preparadorFisicoRepository.atualizarPreparadorFisico(preparador);
        }
        if(operacao.equals("Deletar")){
            preparadorFisicoRepository.deletarPreparadorFisico(preparador);
        }
        return "redirect:/";

    }

    @PostMapping("/pais")
    public String operacaoPaisSede(@RequestParam("operacao") String operacao,@RequestParam("nomePais") String nomePais,@RequestParam("numeroJogosDoPais")int numeroJogosDoPais,
                                   @RequestParam("numeroEstadios") int numeroEstadios,@RequestParam("nomeMascote")String nomeMascote){
        PaisSede pais = new PaisSede(nomePais,numeroJogosDoPais,numeroEstadios,nomeMascote);
        if(operacao.equals("Inserir")){

            paisSedeRepository.inserirPaisSede(pais);
        }
        if(operacao.equals("Alterar")){
            paisSedeRepository.atualizarPaisSede(pais);
        }
        if(operacao.equals("Deletar")){
            paisSedeRepository.deletarPaisSede(pais);
        }
        return "redirect:/";

    }

    @PostMapping("/mascote")
    public String operacaoMascote(@RequestParam("operacao") String operacao,@RequestParam("nome") String nome, @RequestParam("id") int id,@RequestParam("fkInscricaoSelecao")int fkInscricaoSelecao){
        Mascote mascote = new Mascote(nome,id,fkInscricaoSelecao);
        if(operacao.equals("Inserir")){

            mascoteRepository.inserirMascote(mascote);
        }
        if(operacao.equals("Alterar")){
            mascoteRepository.atualizarMascote(mascote);
        }
        if(operacao.equals("Deletar")){
            mascoteRepository.deletarMascote(mascote);
        }
        return "redirect:/";

    }

    @PostMapping("/jogo")
    public String operacaoJogo(@RequestParam("operacao") String operacao,@RequestParam("nomePais") int id, @RequestParam("placar")String placar,
                               @RequestParam("data") LocalDateTime data, @RequestParam("vencedor")String vencedor,@RequestParam("perdedor")String perdedor, @RequestParam("tipoJogo")String tipoJogo){
        Jogo jogo = new Jogo(id,data,placar,vencedor,perdedor,tipoJogo);
        if(operacao.equals("Inserir")){

            jogoRepository.inserirJogo(jogo);
        }
        if(operacao.equals("Alterar")){
            jogoRepository.atualizarJogo(jogo);
        }
        if(operacao.equals("Deletar")){
            jogoRepository.deletarJogo(jogo);
        }
        return "redirect:/";
    }

    @PostMapping("/estadio")
    public String operacaoEstadio(@RequestParam("operacao") String operacao,@RequestParam("id") int id,@RequestParam("cidade")String cidade,@RequestParam("capacidadeMaxima") int capacidadeMaxima,
                                  @RequestParam("nome")String nome, @RequestParam("fkPaisSedeNomePais") String fkPaisSedeNomePais){
        Estadio estadio = new Estadio(id,cidade,capacidadeMaxima,nome,fkPaisSedeNomePais);
        if(operacao.equals("Inserir")){

            estadioRepository.inserirEstadio(estadio);
        }
        if(operacao.equals("Alterar")){
            estadioRepository.atualizarEstadio(estadio);
        }
        if(operacao.equals("Deletar")){
            estadioRepository.deletarEstadio(estadio);
        }
        return "redirect:/";
    }

    @PostMapping("/comissao")
    public String operacaoComissaoTecnica(@RequestParam("operacao") String operacao,@RequestParam("nome") String nome,@RequestParam("nacionalidade")String nacionalidade,
                                          @RequestParam("inscricao") int inscricao,@RequestParam("fkInscricaoSelecao")int fkInscricaoSelecao){
        ComissaoTecnica comissao = new ComissaoTecnica(nome,nacionalidade,inscricao,fkInscricaoSelecao);
        if(operacao.equals("Inserir")){

            comissaoTecnicaRepository.inserirComissaoTecnica(comissao);
        }
        if(operacao.equals("Alterar")){
            comissaoTecnicaRepository.atualizarComissaoTecnica(comissao);
        }
        if(operacao.equals("Deletar")){
            comissaoTecnicaRepository.deletarComissaoTecnica(comissao);
        }
        return "redirect:/";

    }

    @PostMapping("/auxiliar")
    public String operacaoAuxiliarTecnico(@RequestParam("operacao") String operacao,@RequestParam("fkComissaoTecnicaInscricao") int fkComissaoTecnicaInscricao,@RequestParam("especialidade")String especialidade,@RequestParam("focoSetorial") String focoSetorial){
        AuxiliarTecnico auxiliarTecnico = new AuxiliarTecnico(fkComissaoTecnicaInscricao,especialidade,focoSetorial);

        if(operacao.equals("Inserir")){

            auxiliarTecnicoRepository.inserirAuxiliarTecnico(auxiliarTecnico);
        }
        if(operacao.equals("Alterar")){
            auxiliarTecnicoRepository.atualizarAuxiliarTecnico(auxiliarTecnico);
        }
        if(operacao.equals("Deletar")){
            auxiliarTecnicoRepository.deletarAuxiliarTecnico(auxiliarTecnico);
        }
        return "redirect:/";

    }

    @PostMapping("/grupo")
    public String operacaoGrupo(@RequestParam("operacao") String operacao,@RequestParam("letraIdentificadora") char letraIdentificadora,@RequestParam("lugar1")String lugar1,@RequestParam("lugar2") String lugar2,@RequestParam("lugar3") String lugar3, @RequestParam("Lugar4") String lugar4){
        Grupo grupo = new Grupo(letraIdentificadora,lugar1,lugar2,lugar3,lugar4);
        if(operacao.equals("Inserir")){

            grupoRepository.inserirGrupo(grupo);
        }
        if(operacao.equals("Alterar")){
            grupoRepository.atualizarGrupo(grupo);
        }
        if(operacao.equals("Deletar")){
            grupoRepository.deletarGrupo(grupo);
        }
        return "redirect:/";
    }

}
