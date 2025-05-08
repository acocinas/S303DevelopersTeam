package com.model;

import com.interfaces.Observer;
import lombok.Getter;

@Getter
public class ObserverTest implements Observer {

    private boolean notified = false;
    private String lastMessage = "";

    @Override
    public void getNotification(String message) {
        this.notified = true;
        this.lastMessage = message;
    }
}

