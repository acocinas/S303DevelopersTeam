package com.model;

import com.interfaces.Observable;
import com.interfaces.Observer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Player implements Observable {

	private List<Observer> observers;
	@Getter
	private int id;
	@Getter
	private String name;
	@Getter
	private String email;
	@Getter
	private int roomProgress;
	@Getter
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
			observer.update(String.valueOf(roomProgress));
		}
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