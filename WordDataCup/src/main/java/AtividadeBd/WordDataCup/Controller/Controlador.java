package AtividadeBd.WordDataCup.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Controlador{

    @GetMapping("/")
    public String retornarPagina(){
        return "home";
    }
}