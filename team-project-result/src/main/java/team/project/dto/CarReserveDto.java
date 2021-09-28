package team.project.dto;

import lombok.Data;

@Data
public class CarReserveDto {
	private int carIndex;
	private int userIndex;
	private int reserveDateStart;
	private int reserveDateEnd;
	private int carReservePrice;
}
