package team.project.service;

import java.util.HashMap;
import java.util.List;

import team.project.dto.LicenseDto;
import team.project.dto.ReviewDto;
import team.project.dto.UserDto;

public interface UserService {
	UserDto getUser(HashMap<String, String> map) throws Exception;
	UserDto getUserFromSession(HashMap<String, String> map) throws Exception;
	

	void vueUserJoin(UserDto vueDto) throws Exception;
	void vueUserUpdate(UserDto vueDto) throws Exception;
	int vueVerifyEmail(String userId) throws Exception;
	UserDto vueUserDetail(int userIndex) throws Exception;
	void setProfile(UserDto userDto) throws Exception;
	UserDto getProfile(int userIndex) throws Exception;
	void cardReg(UserDto userDto) throws Exception;
	void driverLicense(LicenseDto licenseDto) throws Exception;
	LicenseDto licenseDetail(int userIndex) throws Exception;
	List<ReviewDto> ReviewSelect(int userIndex) throws Exception;
}
