package team.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.project.dto.AdminPageDto;
import team.project.mapper.AdminPageMapper;

@Service
public class AdminPageServiceImpl implements AdminPageService{

	@Autowired
	private AdminPageMapper adminMapper;

	@Override
	public List<AdminPageDto> AccountManageList() throws Exception {
		return adminMapper.AccountManageList();
	}

//	@Override
//	public void isBlockedUpdate(HashMap<String, Integer> map) throws Exception {
//		adminMapper.isBlockedUpdate(map);
//	}

	@Override
	public void isBlockedUpdate(int userIndex, int isBlock) throws Exception {
//		adminMapper.isBlockedUpdate(map);
		adminMapper.isBlockedUpdate(userIndex, isBlock);
	}

	@Override
	public List<AdminPageDto> CarManageList() throws Exception {

		return adminMapper.CarManageList();
	}

	@Override
	public void CarUpdate(int carIndex, String carModel, int carFuel, int carClear) throws Exception {

		adminMapper.CarUpdate(carIndex, carModel, carFuel, carClear);
	}

	@Override
	public void CarInfoInsert(AdminPageDto adDto) throws Exception {
		
		adminMapper.CarInfoInsert(adDto);
	}
	
	@Override
	public void adminCarDelete(int carIndex) throws Exception {

		adminMapper.adminCarDelete(carIndex);
	}
	
	@Override
	public AdminPageDto CarEditInfo(int carIndex) throws Exception {
		
		return adminMapper.CarEditInfo(carIndex);
	}
	
	@Override
	public void CarEditSubmit(AdminPageDto adDto) throws Exception {
		
		adminMapper.CarEditSubmit(adDto);
	}
	
	@Override
	public List<AdminPageDto> ReviewManageList() throws Exception {

		return adminMapper.ReviewManageList();
	}

	@Override
	public void adminReviewDelete(int userIndex, int carIndex) throws Exception {

		adminMapper.adminReviewDelete(userIndex, carIndex);
	}
	
	@Override
	public List<AdminPageDto> ReviewManageUserList(int userIndex) throws Exception {
		
		return adminMapper.ReviewManageUserList(userIndex);
	}
}
