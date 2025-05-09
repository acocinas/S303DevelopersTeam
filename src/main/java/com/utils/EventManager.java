package com.utils;

import com.interfaces.Observable;
import com.interfaces.Observer;

import java.util.ArrayList;
import java.util.List;

public class EventManager implements Observable {
    private List<Observer> observers;

    public EventManager(List<Observer> observers) {

        this.observers = observers != null ? observers : new ArrayList<>();
    }
    @Override
    public void addObserver(Observer observer) {

        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {

        observers.remove(observer);
    }
    @Override
    public void notifyObservers() {
        notifyObservers("⚠️ A change occurred.");
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.getNotification(message);
        }
    }
}