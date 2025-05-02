package com.model;

import java.time.LocalDateTime;

public class TicketSale {
	private int id;
	private Player player;
	private Room room;
	private double price;
	private LocalDateTime saleDate;

	public TicketSale(int id, Player player, Room room, double price, LocalDateTime saleDate) {
		if (price < 0) throw new IllegalArgumentException("Price must be non-negative");
		if (player == null || room == null || saleDate == null)
			throw new IllegalArgumentException("Player, Room, and saleDate cannot be null");

		this.id = id;
		this.player = player;
		this.room = room;
		this.price = price;
		this.saleDate = saleDate;
	}

	public int getId() {
		return id;
	}

	public Player getPlayer() {
		return player;
	}

	public Room getRoom() {
		return room;
	}

	public double getPrice() {
		return price;
	}

	public LocalDateTime getSaleDate() {
		return saleDate;
	}

	@Override
	public String toString() {
		return "TicketSale{" +
				"id=" + id +
				", player=" + player +
				", room=" + room +
				", price=" + price +
				", saleDate=" + saleDate +
				'}';
	}
}
