package team.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import team.project.dto.AdminPageDto;

@Mapper
public interface AdminPageMapper {
	
	List<AdminPageDto> AccountManageList() throws Exception;

//	void isBlockedUpdate(HashMap<String, Integer> map) throws Exception;
	void isBlockedUpdate(@Param("userIndex") int userIndex, @Param("isBlock") int isBlock) throws Exception;

	List<AdminPageDto> CarManageList() throws Exception;

	void CarUpdate(@Param("carIndex") int carIndex, @Param("carModel") String carModel, @Param("carFuel") int carFuel,
			@Param("carClear") int carClear) throws Exception;

	void CarInfoInsert(AdminPageDto adDto) throws Exception;
	
	void adminCarDelete(@Param("carIndex") int carIndex) throws Exception;
	
	AdminPageDto CarEditInfo(int carIndex) throws Exception;
	
	void CarEditSubmit(AdminPageDto adDto) throws Exception;
	
	List<AdminPageDto> ReviewManageList() throws Exception;

	void adminReviewDelete(@Param("userIndex") int userIndex, @Param("carIndex") int carIndex) throws Exception;
	
	List<AdminPageDto> ReviewManageUserList(@Param("userIndex") int userIndex) throws Exception;
}
