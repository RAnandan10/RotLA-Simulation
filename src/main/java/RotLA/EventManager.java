package RotLA;

// This interface is used to describe the methods needed for a publisher class
public interface EventManager {
    void registerSubscriber(Observers obv);
    void removeSubscriber(Observers obv);
    void notifySubscribers(String str);
}

