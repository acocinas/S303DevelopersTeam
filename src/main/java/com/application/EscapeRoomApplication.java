package com.application;

import com.controller.EscapeRoomVirtual;
import com.dao.DAOManager;
import com.model.Player;

public class EscapeRoomApplication {

	public void initialize() {
		Player player1 = Player.builder()
				.name("Alfonso")
				.email("alfonso@example.com")
				.build();

		Player player2 = Player.builder()
				.name("Marat")
				.email("marat@example.com")
				.build();

		Player player3 = Player.builder()
				.name("Pablo")
				.email("pablo@example.com")
				.build();


		DAOManager.getDAOFactory().getPlayerDAO().create(player1);
		DAOManager.getDAOFactory().getPlayerDAO().create(player2);
		DAOManager.getDAOFactory().getPlayerDAO().create(player3);

		EscapeRoomVirtual escapeRoom = new EscapeRoomVirtual();
		escapeRoom.start();
	}
}