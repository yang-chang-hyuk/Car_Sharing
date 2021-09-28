package team.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import team.project.dto.CarDto;
import team.project.dto.UniqueLocationDto;

@Mapper
public interface CarMapper {
	public List<CarDto> getCars() throws Exception;
	public CarDto getCarData(int carIndex) throws Exception;
	public int getCarCountFromLocation(String location) throws Exception;
	public List<CarDto> getCarFromLocation(String location) throws Exception;
	public int getAllowReserveCarCountFromLocation(String location) throws Exception;
	public List<UniqueLocationDto> getCarCountFromUniqueLocation() throws Exception;
	public List<CarDto> getAllowReserveCarFromLocation(String location) throws Exception;
}
