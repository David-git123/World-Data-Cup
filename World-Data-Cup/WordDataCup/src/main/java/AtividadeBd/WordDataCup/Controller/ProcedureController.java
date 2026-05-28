package AtividadeBd.WordDataCup.Controller;

import AtividadeBd.WordDataCup.Repository.ProcedureRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/procedure")
public class ProcedureController {

    private final ProcedureRepository procedureRepository;

    public ProcedureController(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    @PostMapping("/pontuacao")
    public String atualizarPontuacao(
            @RequestParam int inscricao,
            @RequestParam String resultado,
            @RequestParam int gols
    ){

        procedureRepository.atualizarPontuacaoSelecao(
                inscricao,
                resultado,
                gols
        );

        return "Pontuação atualizada";
    }

    @PostMapping("/grupo")
    public String atualizarGrupo(
            @RequestParam char letra
    ){

        procedureRepository.atualizarLugaresGrupo(letra);

        return "Grupo atualizado";
    }
}