package AtividadeBd.WordDataCup.Controller;

import AtividadeBd.WordDataCup.Repository.ViewRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/views")
public class ViewController {

    private final ViewRepository viewRepository;

    public ViewController(ViewRepository viewRepository) {
        this.viewRepository = viewRepository;
    }

    @GetMapping("/escalacao")
    public List<Object[]> escalacao(){

        return viewRepository.listarEscalacaoEstadios();
    }

    @GetMapping("/topo")
    public List<Object[]> topo(){

        return viewRepository.listarSelecoesTopo();
    }
}