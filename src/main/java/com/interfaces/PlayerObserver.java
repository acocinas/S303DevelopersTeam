package com.interfaces;

public class PlayerObserver implements Observer{
	@Override
	public void getNotification(String escapeRoomState){
		System.out.println("Room state is now " + escapeRoomState);
	}
}
