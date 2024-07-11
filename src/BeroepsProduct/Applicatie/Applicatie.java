package BeroepsProduct.Applicatie;

import BeroepsProduct.Model.*;
import BeroepsProduct.Datastructure.*;
import BeroepsProduct.Sensor;
import BeroepsProduct.Verkeerslicht;
import BeroepsProduct.Voertuig;


public class Applicatie public static void main(String[] args) {
    Verkeerslicht noordLicht = new Verkeerslicht();
    Verkeerslicht zuidLicht = new Verkeerslicht();
    Verkeerslicht oostLicht = new Verkeerslicht();
    Verkeerslicht westLicht = new Verkeerslicht();

    Sensor sensor1 = new Sensor1();
    Sensor sensor2 = new Sensor2();
    Sensor sensor3 = new Sensor3();
    Sensor sensor4 = new Sensor4();

    Queue<Voertuig> noordQueue = new Queue<>();
    Queue<Voertuig> zuidQueue = new Queue<>();
    Queue<Voertuig> oostQueue = new Queue<>();
    Queue<Voertuig> westQueue = new Queue<>();

    Stack<Voertuig> reversePlaybackStack = new Stack<>();

    // Simulatie logica
    while (!alleVoertuigenDoorgegaan(noordQueue, zuidQueue, oostQueue, westQueue)) {
        // Logica om verkeerslichten te beheren
        if (noordLicht.getToestand().equals("groen")) {
            // Voertuigen laten passeren op basis van prioriteit
            while (!noordQueue.isEmpty()) {
                Voertuig voertuig = noordQueue.dequeue();
                if (voertuig.getPrioriteit().equals("politie") || voertuig.getPrioriteit().equals("brandweer") || voertuig.getPrioriteit().equals("ambulance")) {
                    // Voertuig heeft prioriteit
                    // Implementeer logica om voertuig door te laten
                } else {
                    // Normaal voertuig
                    // Implementeer logica om voertuig door te laten
                }
                // Voeg voertuig toe aan reverse playback stack
                reversePlaybackStack.push(voertuig);
            }
            // Verander verkeerslichttoestand
            noordLicht.veranderToestand("geel");
        } else if (noordLicht.getToestand().equals("geel")) {
            // Implementeer logica voor gele fase
            noordLicht.veranderToestand("rood");
        } else if (noordLicht.getToestand().equals("rood")) {
            // Implementeer logica voor rode fase
            // Activeer sensor 4 voor Noord
            sensor4.activeer();
            // Verander verkeerslichttoestand op basis van sensor 4
            if (sensor4.geenVoertuigenAanwezig()) {
                noordLicht.veranderToestand("groen");
            }
        }

        // Voeg vergelijkbare logica toe voor de andere verkeerslichten en sensoren

        // Dynamische berekeningen voor aantal cycli en reverse playback
        // Implementeer dynamische berekeningen

        // Voeg gebruikersinterface, error handling en tests toe
    }
}

// Methode om te controleren of alle voertuigen zijn doorgegaan
private static boolean alleVoertuigenDoorgegaan(Queue<Voertuig> noord, Queue<Voertuig> zuid, Queue<Voertuig> oost, Queue<Voertuig> west) {
    return noord.isEmpty() && zuid.isEmpty() && oost.isEmpty() && west.isEmpty();
}

