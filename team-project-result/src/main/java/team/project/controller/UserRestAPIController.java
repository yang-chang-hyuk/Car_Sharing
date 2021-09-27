package team.project.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import team.project.dto.LicenseDto;
import team.project.dto.ReviewDto;
import team.project.dto.UserDto;
import team.project.service.UserService;

@RestController
public class UserRestAPIController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 아이디, 비밀번호로 유저 정보 가져오기 (로그인)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/teamcarsharing/api/GetUser", method = RequestMethod.GET)
	public UserDto getUser(@RequestParam HashMap<String, String> map) throws Exception {
		return userService.getUser(map);
	}
	
	/**
	 * 세션 값으로 유저 정보 가져오기
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/teamcarsharing/api/GetUserFromSession", method = RequestMethod.GET)
	public UserDto getUserFromSession(@RequestParam HashMap<String, String> map) throws Exception {
		return userService.getUserFromSession(map);
	}
	
	/**
	 * 회원가입
	 * @param vueDto
	 * @throws Exception
	 */
	@RequestMapping(value="/vue/userJoin", method=RequestMethod.POST)
	public void vueUserJoin(@RequestBody UserDto vueDto) throws Exception {
		userService.vueUserJoin(vueDto);
	}
	
	/**
	 * 회원 정보 수정
	 * @param vueDto
	 * @throws Exception
	 */
	@RequestMapping(value="/vue/userRevise", method=RequestMethod.PUT)
	public void vueUserUpdate(@RequestBody UserDto vueDto) throws Exception {
		userService.vueUserUpdate(vueDto);
	}
	
	/**
	 * 이메일 중복 확인
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/vue/verifyEmail", method=RequestMethod.GET)
	public int vueVerifyEmail(@RequestParam("userId") String userId) throws Exception {
		return userService.vueVerifyEmail(userId);
	}
	
	/**
	 * 유저 디테일 가져오기
	 * @param userIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/vue/userDetail", method=RequestMethod.GET)
	public Object vueUserDetail(@RequestParam("userIndex")int userIndex) throws Exception {
		UserDto user = userService.vueUserDetail(userIndex);
		return user;
	}
	
	/**
	 * 프로필 사진 등록
	 * @param userDto
	 * @throws Exception
	 */
	@RequestMapping(value="/vue/setProfile", method=RequestMethod.PUT)
	public void setProfile(@RequestBody UserDto userDto) throws Exception {
		userService.setProfile(userDto);
	}
	
	/**
	 * 프로필 사진 가져오기
	 * @param userIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/vue/getProfile", method=RequestMethod.GET)
	public UserDto getProfile(@RequestParam("userIndex") int userIndex) throws Exception {
		return userService.getProfile(userIndex);
	}
	
	/**
	 * 카드 등록
	 * @param userDto
	 * @throws Exception
	 */
	@RequestMapping(value="/vue/cardReg", method=RequestMethod.PUT)
	public void cardReg(@RequestBody UserDto userDto) throws Exception {
		userService.cardReg(userDto);
	}
	
	/**
	 * 면허 등록
	 * @param licenseDto
	 * @throws Exception
	 */
	@RequestMapping(value="/vue/driverlicense", method=RequestMethod.POST)
	public void driverLicense(@RequestBody LicenseDto licenseDto) throws Exception {
		userService.driverLicense(licenseDto);
	}
	
	/**
	 * 면허 정보 가져오기
	 * @param userIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/vue/licenseDetail", method=RequestMethod.GET)
	public LicenseDto licenseDetail(@RequestParam("userIndex") int userIndex) throws Exception {
		return userService.licenseDetail(userIndex);
	}
	
	/**
	 * 유저 리뷰 가져오기
	 * @param userIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/vue/getReview", method=RequestMethod.GET)
	public Object ReviewSelect(@RequestParam("userIndex") int userIndex) throws Exception {
		List<ReviewDto> dataList = userService.ReviewSelect(userIndex);
		return dataList;
	}
}
