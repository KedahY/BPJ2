package BeroepsProduct.Model;

public class Voertuig {
    private int volgnummer;
    private String kenteken;
    private String prioriteit;

    public Voertuig(int volgnummer, String kenteken, String prioriteit) {
        this.volgnummer = volgnummer;
        this.kenteken = kenteken;
        this.prioriteit = prioriteit;
    }

    public int getVolgnummer() {
        return volgnummer;
    }

    public String getKenteken() {
        return kenteken;
    }

    public String getPrioriteit() {
        return prioriteit;
    }
}