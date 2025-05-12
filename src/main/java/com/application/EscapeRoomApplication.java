package com.application;

import com.controller.EscapeRoomVirtual;

public class EscapeRoomApplication {

	public void initialize() {
		EscapeRoomVirtual escapeRoom = new EscapeRoomVirtual();
		escapeRoom.start();
	}
}