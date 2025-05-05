package com.model;

import com.interfaces.Observable;
import com.interfaces.Observer;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Data
public class Player {
	private int id;
	private String name;
	private String email;
	private int roomProgress;
	private List<Ticket> tickets;

	public Player(int id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.roomProgress = 0;
		this.tickets = new ArrayList<>();
	}

	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}

	public void advanceRoomProgress() {
		roomProgress++;
	}

	@Override
	public String toString() {
		return "Player{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", roomProgress=" + roomProgress +
				", tickets=" + tickets +
				'}';
	}
}