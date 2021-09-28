package team.project.dto;

import lombok.Data;

@Data
public class AdminPageDto {

	private int userIndex;
	private String userId;
	private String userPw;
	private String userName;
	private int isAdmin;
	private int isBlock;
	
	private int carIndex;
	private String carNumber;
	private String carModel;
	private int carTakePerson;
	private int carFuel;
	private int carClear;
	private String carOption1;
	private String carOption2;
	private String carOption3;
	private String carUsage;
	private String carLocation;
	
	private String reviewDate;
	private String reviewContents;
	private int likes;
}
