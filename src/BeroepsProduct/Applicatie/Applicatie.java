package BeroepsProduct.Applicatie;
import BeroepsProduct.Model.*;
import BeroepsProduct.Datastructure.*;

public class Applicatie {
    public static void main(String[] args) {
        Verkeerslicht noordLicht = new Verkeerslicht();
        Verkeerslicht zuidLicht = new Verkeerslicht();
        Verkeerslicht oostLicht = new Verkeerslicht();
        Verkeerslicht westLicht = new Verkeerslicht();

        Queue<Voertuig> noordQueue = new Queue<>();
        Queue<Voertuig> zuidQueue = new Queue<>();
        Queue<Voertuig> oostQueue = new Queue<>();
        Queue<Voertuig> westQueue = new Queue<>();

        Sensor sensor1 = new Sensor1(oostQueue);
        Sensor sensor2 = new Sensor2(zuidQueue);
        Sensor sensor3 = new Sensor3(westQueue);
        Sensor sensor4 = new Sensor4(noordQueue);

        Stack<Voertuig> reversePlaybackStack = new Stack<>();

        // Voeg voertuigen toe aan de queues zoals beschreven in de opdracht
        noordQueue.enqueue(new Voertuig(1, "ABC123", ""));
        noordQueue.enqueue(new Voertuig(2, "DEF456", ""));
        noordQueue.enqueue(new Voertuig(3, "GHI789", "ambulance"));
        noordQueue.enqueue(new Voertuig(4, "JKL012", ""));

        for (int i = 1; i <= 17; i++) {
            zuidQueue.enqueue(new Voertuig(i, "Z" + i, ""));
        }
        zuidQueue.enqueue(new Voertuig(18, "MNO345", "brandweer"));

        for (int i = 1; i <= 5; i++) {
            oostQueue.enqueue(new Voertuig(i, "E" + i, ""));
        }

        for (int i = 1; i <= 8; i++) {
            westQueue.enqueue(new Voertuig(i, "W" + i, ""));
        }
        westQueue.enqueue(new Voertuig(9, "PQR678", "politie"));
        for (int i = 10; i <= 14; i++) {
            westQueue.enqueue(new Voertuig(i, "W" + i, ""));
        }

        // Simulatie logica
        while (!alleVoertuigenDoorgegaan(noordQueue, zuidQueue, oostQueue, westQueue)) {
            // Noord verkeerslicht logica
            if (noordLicht.getToestand().equals("groen")) {
                while (!noordQueue.isEmpty()) {
                    Voertuig voertuig = noordQueue.dequeue();
                    reversePlaybackStack.push(voertuig);
                }
                noordLicht.veranderToestand("geel");
            } else if (noordLicht.getToestand().equals("geel")) {
                noordLicht.veranderToestand("rood");
            } else if (noordLicht.getToestand().equals("rood")) {
                sensor4.activeer();
                if (sensor4.geenVoertuigenAanwezig()) {
                    noordLicht.veranderToestand("groen");
                }
            }

            // Zuid verkeerslicht logica
            if (zuidLicht.getToestand().equals("groen")) {
                while (!zuidQueue.isEmpty() && (zuidQueue.size() > 10 || sensor2.geenVoertuigenAanwezig())) {
                    Voertuig voertuig = zuidQueue.dequeue();
                    reversePlaybackStack.push(voertuig);
                }
                zuidLicht.veranderToestand("geel");
            } else if (zuidLicht.getToestand().equals("geel")) {
                zuidLicht.veranderToestand("rood");
            } else if (zuidLicht.getToestand().equals("rood")) {
                sensor2.activeer();
                if (sensor2.geenVoertuigenAanwezig()) {
                    zuidLicht.veranderToestand("groen");
                }
            }

            // Oost verkeerslicht logica
            if (oostLicht.getToestand().equals("groen")) {
                while (!oostQueue.isEmpty()) {
                    Voertuig voertuig = oostQueue.dequeue();
                    reversePlaybackStack.push(voertuig);
                }
                oostLicht.veranderToestand("geel");
            } else if (oostLicht.getToestand().equals("geel")) {
                oostLicht.veranderToestand("rood");
            } else if (oostLicht.getToestand().equals("rood")) {
                sensor1.activeer();
                if (sensor1.geenVoertuigenAanwezig()) {
                    oostLicht.veranderToestand("groen");
                }
            }

            // West verkeerslicht logica
            if (westLicht.getToestand().equals("groen")) {
                while (!westQueue.isEmpty() && (westQueue.size() > 10 || sensor3.geenVoertuigenAanwezig())) {
                    Voertuig voertuig = westQueue.dequeue();
                    reversePlaybackStack.push(voertuig);
                }
                westLicht.veranderToestand("geel");
            } else if (westLicht.getToestand().equals("geel")) {
                westLicht.veranderToestand("rood");
            } else if (westLicht.getToestand().equals("rood")) {
                sensor3.activeer();
                if (sensor3.geenVoertuigenAanwezig()) {
                    westLicht.veranderToestand("groen");
                }
            }
        }
    }

    // Methode om te controleren of alle voertuigen zijn doorgegaan
    private static boolean alleVoertuigenDoorgegaan(Queue<Voertuig> noord, Queue<Voertuig> zuid, Queue<Voertuig> oost, Queue<Voertuig> west) {
        return noord.isEmpty() && zuid.isEmpty() && oost.isEmpty() && west.isEmpty();
    }
}

