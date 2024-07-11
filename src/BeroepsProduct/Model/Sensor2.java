package BeroepsProduct.Model;

import BeroepsProduct.Datastructure.Queue;

public class Sensor2 implements Sensor {
    private Queue<Voertuig> queue;

    public Sensor2(Queue<Voertuig> queue) {
        this.queue = queue;
    }

    @Override
    public void activeer() {
        // Sensor 2: Controleert of er meer dan 10 voertuigen zijn
        // Zo ja, blijft het licht op groen staan tot er 10 voertuigen zijn weggereden
    }

    @Override
    public boolean geenVoertuigenAanwezig() {
        return queue.size() <= 10;
    }
}