package com.application;

import com.controller.EscapeRoomVirtual;
import com.dao.DAOManager;
import com.model.Player;

public class EscapeRoomApplication {

	public void initialize() {
		Player player = Player.builder()
				.name("Alfonso")
				.email("123@efsf.com")
				.build();

		DAOManager.getDAOFactory().getPlayerDAO().create(player);

		EscapeRoomVirtual escapeRoom = new EscapeRoomVirtual();
		escapeRoom.start();
	}
}