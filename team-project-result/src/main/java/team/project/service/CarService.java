package team.project.service;

import java.util.List;

import team.project.dto.CarDto;
import team.project.dto.UniqueLocationDto;

public interface CarService {
	public List<CarDto> getCars() throws Exception;
	public CarDto getCarData(int carIndex) throws Exception;
	public int getCarCountFromLocation(String location) throws Exception;
	public List<CarDto> getCarFromLocation(String location) throws Exception;
	public int getAllowReserveCarCountFromLocation(String location) throws Exception;
	public List<CarDto> getAllowReserveCarFromLocation(String location) throws Exception;
	public List<UniqueLocationDto> getCarCountFromUniqueLocation() throws Exception;
}
