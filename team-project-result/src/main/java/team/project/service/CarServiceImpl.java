package team.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.project.dto.CarDto;
import team.project.dto.UniqueLocationDto;
import team.project.mapper.CarMapper;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarMapper carMapper;
	
	@Override
	public List<CarDto> getCars() throws Exception {
		return carMapper.getCars();
	}

	@Override
	public CarDto getCarData(int carIndex) throws Exception {
		return carMapper.getCarData(carIndex);
	}

	@Override
	public int getCarCountFromLocation(String location) throws Exception {
		return carMapper.getCarCountFromLocation(location);
	}

	@Override
	public List<CarDto> getCarFromLocation(String location) throws Exception {
		return carMapper.getCarFromLocation(location);
	}

	@Override
	public int getAllowReserveCarCountFromLocation(String location) throws Exception {
		return carMapper.getAllowReserveCarCountFromLocation(location);
	}
	
	@Override
	public List<CarDto> getAllowReserveCarFromLocation(String location) throws Exception {
		return carMapper.getAllowReserveCarFromLocation(location);
	}
	
	@Override
	public List<UniqueLocationDto> getCarCountFromUniqueLocation() throws Exception {
		return carMapper.getCarCountFromUniqueLocation();
	}
	
}
