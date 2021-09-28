package team.project.service;

import java.util.List;

import team.project.dto.CarDto;
import team.project.dto.CarReserveDto;

public interface CarReserveService {
	CarReserveDto getCarReservedData(int carIndex) throws Exception;
	int isCarReserved(int carIndex) throws Exception;
	CarReserveDto getUserCarReservedData(int userIndex) throws Exception;
	int isUserCarReserved(int userIndex) throws Exception;
	List<CarDto> getReserveCarFromLocation(String location) throws Exception;
	void carReserve(CarReserveDto carReserve) throws Exception;
	void deleteUserReserveCar(int userIndex) throws Exception;
}
