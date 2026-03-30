package AtividadeBd.WordDataCup.Service;

import AtividadeBd.WordDataCup.Model.Selecao;
import AtividadeBd.WordDataCup.Repository.JogadorRepository;
import AtividadeBd.WordDataCup.Repository.SelecaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceEntidades {
    @Autowired
    JogadorRepository jogadorRepository;
    @Autowired
    SelecaoRepository selecaoRepository;

    //Implementar depois
}
