package team.project.service;

import java.util.List;

import team.project.dto.AdminPageDto;

public interface AdminPageService {

	List<AdminPageDto> AccountManageList() throws Exception;

//	void isBlockedUpdate(HashMap<String, Integer> map) throws Exception;
	void isBlockedUpdate(int userIndex, int isBlock) throws Exception;

	List<AdminPageDto> CarManageList() throws Exception;

	void CarUpdate(int carIndex, String carModel, int carFuel, int carClear) throws Exception;
	
	void CarInfoInsert(AdminPageDto adDto) throws Exception;
	
	void adminCarDelete(int carIndex) throws Exception;
	
	AdminPageDto CarEditInfo(int carIndex) throws Exception;
	
	void CarEditSubmit(AdminPageDto adDto) throws Exception;

	List<AdminPageDto> ReviewManageList() throws Exception;

	void adminReviewDelete(int userIndex, int carIndex) throws Exception;
	
	List<AdminPageDto> ReviewManageUserList(int userIndex) throws Exception;
}
