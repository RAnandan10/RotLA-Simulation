package RotLA;

import java.util.ArrayList;

public class Publisher implements EventManager {

    private ArrayList<Observers> listeners = new ArrayList<>();

    public void registerSubscriber(Observers obv) {
        listeners.add(obv);
    }

    public void removeSubscriber(Observers obv) {
        listeners.remove(obv);
    }

    public void notifySubscribers(String str) {
        for (Observers obv : listeners)
            obv.update(str);
    }
}
