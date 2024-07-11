package BeroepsProduct.Model;

import BeroepsProduct.Datastructure.Queue;

public class Sensor1  implements Sensor {
    private Queue<Voertuig> queue;

    public Sensor1(Queue<Voertuig> queue) {
        this.queue = queue;
    }

    @Override
    public void activeer() {
        // Sensor 1: Controleert of er voertuigen aanwezig zijn
        // Zo niet, slaat het groen licht eenmalig over
    }

    @Override
    public boolean geenVoertuigenAanwezig() {
        return queue.isEmpty();
    }
}

// Implementeer Sensor2, Sensor3, Sensor4 op vergelijkbare wijze