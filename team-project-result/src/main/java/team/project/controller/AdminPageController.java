package team.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import team.project.dto.AdminPageDto;
import team.project.service.AdminPageService;

@RestController
public class AdminPageController {

	@Autowired
	private AdminPageService adminService;

	//유저 정보 받아오는 컨트롤러
	@RequestMapping(value = "/team3/accountadminget", method = RequestMethod.GET)
	public Object AccountManageList() throws Exception {
		List<AdminPageDto> accountList = adminService.AccountManageList();

		return accountList;
	}

//	@RequestMapping(value="/team3/isblocktruefalse", method=RequestMethod.PUT)
//	public void isBlockedUpdate(@RequestBody adminAccountDto isBlocked) throws Exception {
//		adacService.isBlockedUpdate(isBlocked);
//	}

	//유저 차단여부를 정하는 컨트롤러
	@RequestMapping(value = "/team3/setblock", method = RequestMethod.GET)
	public void setBlock(@RequestParam("userIndex") int userIndex, @RequestParam("isBlock") int isBlock)
			throws Exception {
//		System.out.println(userIndex);
//		adacService.isBlockedUpdate();
		adminService.isBlockedUpdate(userIndex, isBlock);
	}

	//차량 정보 받아오는 컨트롤러
	@RequestMapping(value = "/team3/caradminget", method = RequestMethod.GET)
	public Object CarManageList() throws Exception {
		List<AdminPageDto> carList = adminService.CarManageList();

		return carList;
	}

	//차량 정보 리스트에서 정보 업데이트를 시키는 컨트롤러
	@RequestMapping(value = "/team3/carinfoupdate", method = RequestMethod.GET)
	public void CarInfoUpdate(@RequestParam("carIndex") int carIndex, @RequestParam("carModel") String carModel,
			@RequestParam("carFuel") int carFuel, @RequestParam("carClear") int carClear) throws Exception {

		adminService.CarUpdate(carIndex, carModel, carFuel, carClear);
	}

	//차량 정보 넣는 컨트롤러
	@RequestMapping(value = "/team3/carinsert", method = RequestMethod.POST)
	public void CarInfoInsert(@RequestBody AdminPageDto adDto) throws Exception {

		adminService.CarInfoInsert(adDto);
	}
	
	//차량 수정 할때 정보 받아오는 컨트롤러
	@RequestMapping(value = "/team3/careditinfo", method = RequestMethod.GET)
	public Object CarEditinfo(@RequestParam("carIndex") int carIndex) throws Exception{
		AdminPageDto carinfo = adminService.CarEditInfo(carIndex);
		
		return carinfo;
	}
	
	//차량 수정하고 데이터베이스에 등록하는 컨트롤러
	@RequestMapping(value = "/team3/careditsubmit", method= RequestMethod.POST)
	public void CarEditSubmit(@RequestBody AdminPageDto admindto) throws Exception {
		
		adminService.CarEditSubmit(admindto);
	}

	//차량 정보 삭제를 하는 컨트롤러(에러가 남)
	@RequestMapping(value = "/team3/cardelete", method = RequestMethod.GET)
	public void adminCarDelete(@RequestParam("carIndex") int carIndex) throws Exception {
		adminService.adminCarDelete(carIndex);
	}

	//댓글 정보 받아오는 컨트롤러
	@RequestMapping(value = "/team3/reviewadminget", method = RequestMethod.GET)
	public Object ReviewManagelist() throws Exception {
		List<AdminPageDto> reviewList = adminService.ReviewManageList();

		return reviewList;
	}

	//댓글 삭제를 하는 컨트롤러
	@RequestMapping(value = "/team3/reviewdelete", method = RequestMethod.GET)
	public void adminReviewDelete(@RequestParam("userIndex") int userIndex, @RequestParam("carIndex") int carIndex)
			throws Exception {
		adminService.adminReviewDelete(userIndex, carIndex);
	}

	//유저 정보와 댓글을 동시에 받아오는 컨트롤러(유저가 작성한 댓글을 따로보여주는)
	@RequestMapping(value = "/team3/reviewadminuserget", method = RequestMethod.GET)
	public Object ReviewManageUserList(@RequestParam("userIndex") int userIndex) throws Exception {
		List<AdminPageDto> reviewusLi = adminService.ReviewManageUserList(userIndex);

		return reviewusLi;
	}

}
