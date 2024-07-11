package BeroepsProduct.Model;

import BeroepsProduct.Datastructure.Queue;

public class Sensor4 implements Sensor {
    private Queue<Voertuig> queue;

    public Sensor4(Queue<Voertuig> queue) {
        this.queue = queue;
    }

    @Override
    public void activeer() {
        // Sensor 4: Controleert of er minder dan 5 voertuigen zijn
        // Zo ja, blijft het licht op groen tot alle voertuigen zijn weggereden
        if (queue.size() < 5) {
            while (!queue.isEmpty()) {
                queue.dequeue();
            }
        }
    }

    @Override
    public boolean geenVoertuigenAanwezig() {
        return queue.isEmpty();
    }
}

