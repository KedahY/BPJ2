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
        noordQueue.enqueue(new Voertuig(1, "PM-23-43", ""));
        noordQueue.enqueue(new Voertuig(2, "PM-26-40", ""));
        noordQueue.enqueue(new Voertuig(3, "PM-22-49", "ambulance"));
        noordQueue.enqueue(new Voertuig(4, "PM-28-48", ""));

        for (int i = 1; i <= 17; i++) {
            zuidQueue.enqueue(new Voertuig(i, "Z" + i, ""));
        }
        zuidQueue.enqueue(new Voertuig(18, "PH-78-29", "brandweer"));

        for (int i = 1; i <= 5; i++) {
            oostQueue.enqueue(new Voertuig(i, "E" + i, ""));
        }

        for (int i = 1; i <= 8; i++) {
            westQueue.enqueue(new Voertuig(i, "W" + i, ""));
        }
        westQueue.enqueue(new Voertuig(9, "PC-34-56", "politie"));
        for (int i = 10; i <= 14; i++) {
            westQueue.enqueue(new Voertuig(i, "W" + i, ""));
        }

        // Simulatie logica
        while (!alleVoertuigenDoorgegaan(noordQueue, zuidQueue, oostQueue, westQueue)) {
            // Noord verkeerslicht logica
            if (noordLicht.getToestand().equals("groen")) {
                while (!noordQueue.isEmpty()) {
                    Voertuig voertuig = noordQueue.dequeue();
                    if (voertuig.getType().equals("ambulance") || voertuig.getType().equals("politie") || voertuig.getType().equals("brandweer")) {
                        System.out.println("Noord: Voertuig met kenteken " + voertuig.getKenteken() + " (type: " + voertuig.getType() + ") heeft voorrang en rijdt door.");
                        // Leeg de rest van de queue voor deze richting
                        while (!noordQueue.isEmpty()) {
                            Voertuig stopVoertuig = noordQueue.dequeue();
                            System.out.println("Noord: Voertuig met kenteken " + stopVoertuig.getKenteken() + " moet stoppen.");
                            reversePlaybackStack.push(stopVoertuig);
                        }
                        // Stop andere richtingen ook
                        stopVoertuigenInAndereRichtingen(zuidQueue, oostQueue, westQueue);
                    } else {
                        System.out.println("Noord: Voertuig met kenteken " + voertuig.getKenteken() + " moet stoppen.");
                        reversePlaybackStack.push(voertuig);
                    }
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

            // West verkeerslicht logica
            if (westLicht.getToestand().equals("groen")) {
                while (!westQueue.isEmpty()) {
                    Voertuig voertuig = westQueue.dequeue();
                    if (voertuig.getType().equals("ambulance") || voertuig.getType().equals("politie") || voertuig.getType().equals("brandweer")) {
                        System.out.println("West: Voertuig met kenteken " + voertuig.getKenteken() + " (type: " + voertuig.getType() + ") heeft voorrang en rijdt door.");
                        // Leeg de rest van de queue voor deze richting
                        while (!westQueue.isEmpty()) {
                            Voertuig stopVoertuig = westQueue.dequeue();
                            System.out.println("West: Voertuig met kenteken " + stopVoertuig.getKenteken() + " moet stoppen.");
                            reversePlaybackStack.push(stopVoertuig);
                        }
                        // Stop andere richtingen ook
                        stopVoertuigenInAndereRichtingen(noordQueue, zuidQueue, oostQueue);
                    } else {
                        System.out.println("West: Voertuig met kenteken " + voertuig.getKenteken() + " moet stoppen.");
                        reversePlaybackStack.push(voertuig);
                    }
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

            // Oost verkeerslicht logica (aangenomen dat Oost-voertuigen altijd stoppen tenzij anders vermeld)
            if (oostLicht.getToestand().equals("groen")) {
                while (!oostQueue.isEmpty()) {
                    Voertuig voertuig = oostQueue.dequeue();
                    System.out.println("Oost: Voertuig met kenteken " + voertuig.getKenteken() + " moet stoppen.");
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

            // Zuid verkeerslicht logica
            if (zuidLicht.getToestand().equals("groen")) {
                while (!zuidQueue.isEmpty()) {
                    Voertuig voertuig = zuidQueue.dequeue();
                    if (voertuig.getType().equals("brandweer")) {
                        System.out.println("Zuid: Voertuig met kenteken " + voertuig.getKenteken() + " (type: " + voertuig.getType() + ") heeft voorrang en rijdt door.");
                        // Leeg de rest van de queue voor deze richting
                        while (!zuidQueue.isEmpty()) {
                            Voertuig stopVoertuig = zuidQueue.dequeue();
                            System.out.println("Zuid: Voertuig met kenteken " + stopVoertuig.getKenteken() + " moet stoppen.");
                            reversePlaybackStack.push(stopVoertuig);
                        }
                        // Stop andere richtingen ook
                        stopVoertuigenInAndereRichtingen(noordQueue, westQueue, oostQueue);
                    } else {
                        System.out.println("Zuid: Voertuig met kenteken " + voertuig.getKenteken() + " rijdt door.");
                    }
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

    private static void stopVoertuigenInAndereRichtingen(Queue<Voertuig> zuidQueue, Queue<Voertuig> oostQueue, Queue<Voertuig> westQueue) {
    }

    private static boolean alleVoertuigenDoorgegaan(Queue<Voertuig> noordQueue, Queue<Voertuig> zuidQueue, Queue<Voertuig> oostQueue, Queue<Voertuig> westQueue) {
        return noordQueue.isEmpty() && zuidQueue.isEmpty() && oostQueue.isEmpty() && westQueue.isEmpty();
    }

    private static void stopVoertuigenInAndereRichtingen(Queue<Voertuig> noordQueue, Queue<Voertuig> zuidQueue, Queue<Voertuig> oostQueue, Queue<Voertuig> westQueue) {
        while (!noordQueue.isEmpty()) {
            Voertuig voertuig = noordQueue.dequeue();
            System.out.println("Noord: Voertuig met kenteken " + voertuig.getKenteken() + " moet stoppen.");
        }
        while (!zuidQueue.isEmpty()) {
            Voertuig voertuig = zuidQueue.dequeue();
            System.out.println("Zuid: Voertuig met kenteken " + voertuig.getKenteken() + " moet stoppen.");
        }
        while (!oostQueue.isEmpty()) {
            Voertuig voertuig = oostQueue.dequeue();
            System.out.println("Oost: Voertuig met kenteken " + voertuig.getKenteken() + " moet stoppen.");
        }
        while (!westQueue.isEmpty()) {
            Voertuig voertuig = westQueue.dequeue();
            System.out.println("West: Voertuig met kenteken " + voertuig.getKenteken() + " moet stoppen.");
        }
    }
}