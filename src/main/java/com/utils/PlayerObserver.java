package com.observerImplementation;

import com.interfaces.Observer;

public class PlayerObserver implements Observer {
	@Override
	public void getNotification(String escapeRoomState){
		System.out.println("Room state is now " + escapeRoomState);
	}
}
