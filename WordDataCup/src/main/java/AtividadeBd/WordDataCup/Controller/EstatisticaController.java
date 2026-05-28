package AtividadeBd.WordDataCup.Controller;

import AtividadeBd.WordDataCup.Repository.EstatisticaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticaController {

    private final EstatisticaRepository estatisticaRepository;

    public EstatisticaController(EstatisticaRepository estatisticaRepository) {
        this.estatisticaRepository = estatisticaRepository;
    }

    @GetMapping("/categoria-estadios")
    public List<Object[]> categoriaEstadios(){

        return estatisticaRepository.retornarCategoriaEstadios();
    }

    @GetMapping("/logs")
    public List<Object[]> logs(){

        return estatisticaRepository.listarLogs();
    }

    @GetMapping("/continentes")
    public List<Object[]> continentes(){

        return estatisticaRepository.mediaIdadeContinente();
    }
}