package BeroepsProduct.Applicatie;
import BeroepsProduct.Model.*;
import BeroepsProduct.Datastructure.*;

import java.util.Scanner;

public class Applicatie {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
                    System.out.println("Noord: Voertuig met kenteken " + voertuig.getKenteken() + " rijdt door.");
                    reversePlaybackStack.push(voertuig);
                }
                noordLicht.veranderToestand("geel");
            } else if (noordLicht.getToestand().equals("geel")) {
                System.out.println("Noord: Licht is geel.");
                noordLicht.veranderToestand("rood");
            } else if (noordLicht.getToestand().equals("rood")) {
                System.out.println("Noord: Licht is rood.");
                sensor4.activeer();
                if (sensor4.geenVoertuigenAanwezig()) {
                    noordLicht.veranderToestand("groen");
                }
            }

            // Zuid verkeerslicht logica
            if (zuidLicht.getToestand().equals("groen")) {
                while (!zuidQueue.isEmpty() && (zuidQueue.size() > 10 || sensor2.geenVoertuigenAanwezig())) {
                    Voertuig voertuig = zuidQueue.dequeue();
                    System.out.println("Zuid: Voertuig met kenteken " + voertuig.getKenteken() + " rijdt door.");
                    reversePlaybackStack.push(voertuig);
                }
                zuidLicht.veranderToestand("geel");
            } else if (zuidLicht.getToestand().equals("geel")) {
                System.out.println("Zuid: Licht is geel.");
                zuidLicht.veranderToestand("rood");
            } else if (zuidLicht.getToestand().equals("rood")) {
                System.out.println("Zuid: Licht is rood.");
                sensor2.activeer();
                if (sensor2.geenVoertuigenAanwezig()) {
                    zuidLicht.veranderToestand("groen");
                }
            }

            // Oost verkeerslicht logica
            if (oostLicht.getToestand().equals("groen")) {
                while (!oostQueue.isEmpty()) {
                    Voertuig voertuig = oostQueue.dequeue();
                    System.out.println("Oost: Voertuig met kenteken " + voertuig.getKenteken() + " rijdt door.");
                    reversePlaybackStack.push(voertuig);
                }
                oostLicht.veranderToestand("geel");
            } else if (oostLicht.getToestand().equals("geel")) {
                System.out.println("Oost: Licht is geel.");
                oostLicht.veranderToestand("rood");
            } else if (oostLicht.getToestand().equals("rood")) {
                System.out.println("Oost: Licht is rood.");
                sensor1.activeer();
                if (sensor1.geenVoertuigenAanwezig()) {
                    oostLicht.veranderToestand("groen");
                }
            }

            // West verkeerslicht logica
            if (westLicht.getToestand().equals("groen")) {
                while (!westQueue.isEmpty() && (westQueue.size() > 10 || sensor3.geenVoertuigenAanwezig())) {
                    Voertuig voertuig = westQueue.dequeue();
                    System.out.println("West: Voertuig met kenteken " + voertuig.getKenteken() + " rijdt door.");
                    reversePlaybackStack.push(voertuig);
                }
                westLicht.veranderToestand("geel");
            } else if (westLicht.getToestand().equals("geel")) {
                System.out.println("West: Licht is geel.");
                westLicht.veranderToestand("rood");
            } else if (westLicht.getToestand().equals("rood")) {
                System.out.println("West: Licht is rood.");
                sensor3.activeer();
                if (sensor3.geenVoertuigenAanwezig()) {
                    westLicht.veranderToestand("groen");
                }
            }

            // Pauzeer voor invoer om de simulatie stapsgewijs te maken
            System.out.println("Druk op Enter om de simulatie voort te zetten...");
            scanner.nextLine();
        }

        // Reverse playback logica
        System.out.println("Alle voertuigen zijn gepasseerd. Begin met reverse playback.");
        while (!reversePlaybackStack.isEmpty()) {
            Voertuig voertuig = reversePlaybackStack.pop();
            System.out.println("Voertuig met kenteken " + voertuig.getKenteken() + " wordt teruggeplaatst.");
        }

        scanner.close();
    }

    // Methode om te controleren of alle voertuigen zijn doorgegaan
    private static boolean alleVoertuigenDoorgegaan(Queue<Voertuig> noord, Queue<Voertuig> zuid, Queue<Voertuig> oost, Queue<Voertuig> west) {
        return noord.isEmpty() && zuid.isEmpty() && oost.isEmpty() && west.isEmpty();
    }
}