package team.project.dto;

import lombok.Data;

@Data
public class ReviewDto {
	private int carIndex;
	private String carModel;
	private String carNumber;
	private int userIndex;
	private String userNickname;
	private String reviewDate;
	private String reviewContents;
	private int likes;
}
