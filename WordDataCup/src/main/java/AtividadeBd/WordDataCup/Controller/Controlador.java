package AtividadeBd.WordDataCup.Controller;

import AtividadeBd.WordDataCup.Model.Jogador;
import AtividadeBd.WordDataCup.Model.Selecao;
import AtividadeBd.WordDataCup.Service.ServiceEntidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Controlador{

    @Autowired
    ServiceEntidades service;

    @GetMapping("/")
    public String retornarPagina(){
        return "home";
    }
    @PostMapping("jogadores")
    public String operacaoJogadores(@ModelAttribute Jogador jogadores){
        return "redirect:/";
        //Redirect impede reenvio duplicado de formulário
    }

    @PostMapping("selecao")
    public String operacaoSelecao(@ModelAttribute Selecao selecao){
        return "redirect:/";
        //Redirect impede reenvio duplicado de formulário
    }
}