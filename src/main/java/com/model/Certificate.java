package com.model;

import java.time.LocalDateTime;

public class Certificate{
	private int id;
	private Player player;
	private LocalDateTime certificateDate;
	private String roomName;

	public Certificate(int id, Player player, String roomName, LocalDateTime certificateDate) {
		this.id = id;
		this.player = player;
		this.roomName = roomName;
		this.certificateDate = certificateDate;
	}

	public int getId() {
		return id;
	}

	public Player getPlayer() {
		return player;
	}

	public LocalDateTime getCertificateDate() {
		return certificateDate;
	}

	public String getRoomName(){
		return roomName;
	}

	public void getCongratulationMessage(){
		System.out.println("Congratulation! You have beaten the room " + roomName + "!");
	}

	@Override
	public String toString() {
		return "Certificate{" +
				"id=" + id +
				", player=" + player +
				", certificateDate=" + certificateDate +
				", roomName='" + roomName + '\'' +
				'}';
	}
}

