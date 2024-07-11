package BeroepsProduct.Model;

public class Verkeerslicht {
    private String toestand; // groen, geel, rood

    public Verkeerslicht() {
        this.toestand = "rood"; // Beginnen met rood
    }

    public String getToestand() {
        return toestand;
    }

    public void veranderToestand(String nieuweToestand) {
        this.toestand = nieuweToestand;
    }
}