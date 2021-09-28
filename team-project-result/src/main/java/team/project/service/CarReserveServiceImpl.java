package team.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.project.dto.CarDto;
import team.project.dto.CarReserveDto;
import team.project.mapper.CarReserveMapper;

@Service
public class CarReserveServiceImpl implements CarReserveService {
	
	@Autowired
	private CarReserveMapper carReserveMapper;

	@Override
	public CarReserveDto getCarReservedData(int carIndex) throws Exception {
		return carReserveMapper.getCarReservedData(carIndex);
	}

	@Override
	public int isCarReserved(int carIndex) throws Exception {
		return carReserveMapper.isCarReserved(carIndex);
	}

	@Override
	public CarReserveDto getUserCarReservedData(int userIndex) throws Exception {
		return carReserveMapper.getUserCarReservedData(userIndex);
	}

	@Override
	public int isUserCarReserved(int userIndex) throws Exception {
		return carReserveMapper.isUserCarReserved(userIndex);
	}

	@Override
	public List<CarDto> getReserveCarFromLocation(String location) throws Exception {
		return carReserveMapper.getReserveCarFromLocation(location);
	}

	@Override
	public void deleteUserReserveCar(int userIndex) throws Exception {
		carReserveMapper.deleteUserReserveCar(userIndex);
	}

	@Override
	public void carReserve(CarReserveDto carReserve) throws Exception {
		carReserveMapper.carReserve(carReserve);
	}
	
}
