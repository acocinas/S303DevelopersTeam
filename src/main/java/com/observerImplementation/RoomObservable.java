package com.observerImplementation;

import com.interfaces.Observable;
import com.interfaces.Observer;
import com.model.Player;

import java.util.ArrayList;
import java.util.List;

public class RoomObservable implements Observable {
    private List<Observer> observers;

    public RoomObservable(List<Player> observers) {
        this.observers = new ArrayList<>();
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
        for (Observer observer : observers) {
            observer.getNotification("Room state has changed!");
        }
    }
}
