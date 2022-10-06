package RotLA;

public interface EventManager {
    void registerSubscriber(Observers obv);
    void removeSubscriber(Observers obv);
    void notifySubscribers(String str);
}

