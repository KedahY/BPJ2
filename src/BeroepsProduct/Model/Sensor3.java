package BeroepsProduct.Model;

import BeroepsProduct.Datastructure.Queue;

public class Sensor3 implements Sensor {
    private Queue<Voertuig> queue;

    public Sensor3(Queue<Voertuig> queue) {
        this.queue = queue;
    }

    @Override
    public void activeer() {
        // Sensor 3: Controleert combinatie van Sensor 1 en 2
        if (queue.isEmpty() || queue.size() > 10) {
            while (!queue.isEmpty() && queue.size() > 10) {
                queue.dequeue();
            }
        }
    }

    @Override
    public boolean geenVoertuigenAanwezig() {
        return queue.isEmpty() || queue.size() <= 10;
    }
}