package AtividadeBd.WordDataCup.Model;
import java.time.LocalDateTime;

public class Jogo {

    private int id;
    private String placar;
    private LocalDateTime data;
    private String vencedor;
    private String perdedor;
    private String tipoJogo;

    public Jogo(int id, LocalDateTime data , String placar, String vencedor, String perdedor, String tipoJogo){
        this.id = id;
        this.placar = placar;
        this.data = data;
        this.vencedor = vencedor;
        this.perdedor = perdedor;
        this.tipoJogo = tipoJogo;
    }

    public Jogo() {
    }

    public int getId() {
        return id;
    }

    public String getPlacar() {
        return placar;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getVencedor() {
        return vencedor;
    }

    public String getPerdedor() {
        return perdedor;
    }

    public String getTipoJogo() {
        return tipoJogo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlacar(String placar) {
        this.placar = placar;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setVencedor(String vencedor) {
        this.vencedor = vencedor;
    }

    public void setPerdedor(String perdedor) {
        this.perdedor = perdedor;
    }

    public void setTipoJogo(String tipoJogo) {
        this.tipoJogo = tipoJogo;
    }
}
