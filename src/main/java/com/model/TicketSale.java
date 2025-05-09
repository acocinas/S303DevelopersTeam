package com.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
public class TicketSale {
	private int id;
	private Player player;
	private Room room;
	private double price;
	private LocalDateTime saleDate;

	public TicketSale(int id, Player player, Room room, double price, LocalDateTime saleDate) {
		if (price < 0) {
			throw new IllegalArgumentException("Price must be non-negative");
		}
		if (player == null || room == null || saleDate == null) {
			throw new IllegalArgumentException("Player, Room, and saleDate cannot be null");
		}

		this.id = id;
		this.player = player;
		this.room = room;
		this.price = price;
		this.saleDate = saleDate;
	}
}
