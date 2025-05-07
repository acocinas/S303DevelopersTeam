package com.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
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

