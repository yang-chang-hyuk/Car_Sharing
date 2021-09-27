package team.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import team.project.dto.CarDto;
import team.project.dto.CarReserveDto;

@Mapper
public interface CarReserveMapper {
	CarReserveDto getCarReservedData(int carIndex) throws Exception;
	int isCarReserved(int carIndex) throws Exception;
	CarReserveDto getUserCarReservedData(int userIndex) throws Exception;
	int isUserCarReserved(int userIndex) throws Exception;
	List<CarDto> getReserveCarFromLocation(String location) throws Exception;
	void carReserve(CarReserveDto carReserve) throws Exception;
	void deleteUserReserveCar(int userIndex) throws Exception;
}
