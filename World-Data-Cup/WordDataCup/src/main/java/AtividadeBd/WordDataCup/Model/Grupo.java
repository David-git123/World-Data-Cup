package AtividadeBd.WordDataCup.Model;

public class Grupo {
    private char letraIdentificadora;
    private String lugar1;
    private String lugar2;
    private String lugar3;
    private String lugar4;


    public Grupo(char letraIdentificadora, String lugar1, String lugar2, String lugar3, String lugar4) {
        this.letraIdentificadora = letraIdentificadora;
        this.lugar1 = lugar1;
        this.lugar2 = lugar2;
        this.lugar3 = lugar3;
        this.lugar4 = lugar4;

    }

    public char getLetraIdentificadora() {
        return letraIdentificadora;
    }

    public String getLugar1() {
        return lugar1;
    }

    public String getLugar2() {
        return lugar2;
    }

    public String getLugar3() {return lugar3;}

    public String getLugar4() {return lugar4;}

    public void setLetraIdentificadora(char letraIdentificadora) {
        this.letraIdentificadora = letraIdentificadora;
    }

    public void setLugar1(String lugar1) {
        this.lugar1 = lugar1;
    }

    public void setLugar2(String lugar2) {
        this.lugar2 = lugar2;
    }

    public void setLugar3(String lugar3) {
        this.lugar3 = lugar3;
    }

    public void setLugar4(String lugar4) {
        this.lugar4 = lugar4;
    }
}

