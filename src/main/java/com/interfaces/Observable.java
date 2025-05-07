package com.interfaces;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
    void notifyObservers(String message);

}
