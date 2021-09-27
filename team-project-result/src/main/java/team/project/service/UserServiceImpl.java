package team.project.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.project.dto.LicenseDto;
import team.project.dto.ReviewDto;
import team.project.dto.UserDto;
import team.project.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;

	@Override
	public UserDto getUser(HashMap<String, String> map) throws Exception {
		return userMapper.getUser(map);
	}

	@Override
	public UserDto getUserFromSession(HashMap<String, String> map) throws Exception {
		return userMapper.getUserFromSession(map);
	}
	
	

	@Override
	public void vueUserJoin(UserDto vueDto) throws Exception {
		userMapper.vueUserJoin(vueDto);

	}
	
	@Override
	public void vueUserUpdate(UserDto vueDto) throws Exception {
		userMapper.vueUserUpdate(vueDto);
	}

	@Override
	public int vueVerifyEmail(String userId) throws Exception {
		return userMapper.vueVerifyEmail(userId);
	}
	
	@Override
	public UserDto vueUserDetail(int userIndex) throws Exception {
		return userMapper.vueUserDetail(userIndex);
	}
	
	@Override
	public void setProfile(UserDto userDto) throws Exception {
		userMapper.setProfile(userDto);
	}
	
	@Override
	public UserDto getProfile(int userIndex) throws Exception {
		return userMapper.getProfile(userIndex);
	}
	
	@Override
	public void cardReg(UserDto userDto) throws Exception {
		userMapper.cardReg(userDto);
	}
	
	@Override
	public void driverLicense(LicenseDto licenseDto) throws Exception {
		userMapper.driverLicense(licenseDto);
	}

	@Override
	public LicenseDto licenseDetail(int userIndex) throws Exception {
		return userMapper.licenseDetail(userIndex);
	}

	@Override
	public List<ReviewDto> ReviewSelect(int userIndex) throws Exception {
		return userMapper.ReviewSelect(userIndex);
	}
}
