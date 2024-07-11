package BeroepsProduct;

public class Verkeerslicht {
    private String toestand; // "groen", "rood", "geel"
    private int duur;

    public Verkeerslicht() {
        this.toestand = "rood"; // Start standaard op rood
        this.duur = 0;
    }

    public String getToestand() {
        return toestand;
    }

    public void veranderToestand(String toestand) {
        this.toestand = toestand;
    }
}
