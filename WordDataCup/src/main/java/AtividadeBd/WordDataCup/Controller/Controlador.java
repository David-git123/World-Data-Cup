package AtividadeBd.WordDataCup.Controller;

import AtividadeBd.WordDataCup.Model.Jogador;
import AtividadeBd.WordDataCup.Model.Selecao;
import AtividadeBd.WordDataCup.Repository.JogadorRepository;
import AtividadeBd.WordDataCup.Repository.SelecaoRepository;
import AtividadeBd.WordDataCup.Service.ServiceEntidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Controlador{

    @Autowired
    JogadorRepository jogadorRepository;
    @Autowired
    SelecaoRepository selecaoRepository;



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
        Selecao selecao = new Selecao(inscricao,mediaIdade,mediaGols,rankingFifa,continente,pontuacao,nome,fk_letra_identificadora);

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
}