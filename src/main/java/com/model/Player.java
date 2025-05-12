package com.model;

import com.interfaces.Observer;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class Player implements Observer {
	private int id;
	private String name;
	private String email;

	@Builder.Default
	private int roomProgress = 0;

	@Builder.Default
	private List<TicketSale> tickets = new ArrayList<>();

	public void addTicket(TicketSale ticket) {
		tickets.add(ticket);
	}

	public void advanceRoomProgress() {
		roomProgress++;
	}

	@Override
	public void getNotification(String message) {
		log.info("📨 Notification for {}: {}", name, message);
	}
}
