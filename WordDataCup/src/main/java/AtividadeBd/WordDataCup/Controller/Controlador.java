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
                                    @RequestParam("posicao") String posicao,@RequestParam("selecao_id") int selecao_id,@RequestParam("nome_atleta") String nome_atleta,
                                    @RequestParam("idade") int idade, Model model){

        Jogador jogador = new Jogador(camisa,inscricao,posicao,selecao_id,nome_atleta,idade);
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
    public String operacaoSelecao(@RequestParam("operacao") String operacao, @RequestParam("n_inscricao") int inscricao,@RequestParam("paisDeOrigem") String paisDeOrigem,
                                  @RequestParam("mediaGols") int mediaGols,@RequestParam("rankingFifa") int rankingFifa,
                                  @RequestParam("mediaIdade") int mediaIdade, Model model){
        Selecao selecao = new Selecao(inscricao,paisDeOrigem,mediaGols,rankingFifa,mediaIdade);

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