package team.project.dto;

import lombok.Data;

@Data
public class UserDto {
	private int userIndex;
	private String userId;
	private String userPw;
	private String userName;
	private String userRegnum;
	private int userRegnumGender;
	private String userProfile;
	private String userNickname;
	private String userPhone;
	private int isAdmin;
	private int isBlock;
	private String userCardNum;
	private String userCardValidity;
	private String userCardPw;
}
