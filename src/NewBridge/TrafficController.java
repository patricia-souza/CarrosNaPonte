package NewBridge;

import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrafficController {

    Queue<Thread> cars;

    public TrafficController() {
        cars = new ArrayDeque<Thread>();
    }

    public synchronized void enterLeft() {
        
        cars.add(Thread.currentThread());

        while (cars.peek() != Thread.currentThread()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(TrafficController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public synchronized void enterRight() {
        
        cars.add(Thread.currentThread());

        while (cars.peek() != Thread.currentThread()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(TrafficController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public synchronized void leaveLeft() {
        if (cars.poll() != Thread.currentThread()) {
            System.err.println("Ocorreu um erro!!!");
        }
        notifyAll();
    }

    public synchronized void leaveRight() {
        if (cars.poll() != Thread.currentThread()) {
            System.err.println("Ocorreu um erro!!!");
        }
        notifyAll();
    }
}

