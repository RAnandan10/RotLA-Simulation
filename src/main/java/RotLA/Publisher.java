package RotLA;

import java.util.ArrayList;

// This class implements EventManager interface and describes methods for a publisher to inherit
public class Publisher implements EventManager {

    private ArrayList<Observers> listeners = new ArrayList<>();                 //Gives the list of current listeners

    // New subscriber is added to list of listeners using this method
    public void registerSubscriber(Observers obv) {
        listeners.add(obv);
    }

    // A subscriber is removed from list of listeners using this method
    public void removeSubscriber(Observers obv) {
        listeners.remove(obv);
    }

    // All subscribers are notified of any event using this method
    public void notifySubscribers(String str) {
        for (Observers obv : listeners)
            obv.update(str);
    }
}
