package com.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;

@Slf4j
@Builder
@AllArgsConstructor
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
		log.info("Congratulation! You have beaten the room {}!", roomName);
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

