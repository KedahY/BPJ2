package BeroepsProduct;

public class Voertuig {
    private int volgnummer;
    private String kentekennummer;
    private int prioriteit; // 0 voor normale voertuigen, hogere getallen voor speciale voertuigen

    public Voertuig(int volgnummer, String kentekennummer, int prioriteit) {
        this.volgnummer = volgnummer;
        this.kentekennummer = kentekennummer;
        this.prioriteit = prioriteit;
    }

    // Getters en setters
    public int getVolgnummer() {
        return volgnummer;
    }

    public String getKentekennummer() {
        return kentekennummer;
    }

    public int getPrioriteit() {
        return prioriteit;
    }
}


