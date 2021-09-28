package team.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import team.project.dto.UniqueLocationDto;
import team.project.service.CarService;

@RestController
public class CarRestAPIController {
	
	@Autowired
	private CarService carService;
	
	//	데이터베이스의 모든 차량 가져오기
	@RequestMapping(value = "/teamcarsharing/api/GetCars", method = RequestMethod.GET)
	public Object getCars() throws Exception {
		return carService.getCars();
	}
	
	//	차량 인덱스로 정보 가져오기
	@RequestMapping(value = "/teamcarsharing/api/GetCarData", method = RequestMethod.GET)
	public Object getCarData(@RequestParam("carIndex") int carIndex) throws Exception {
		return carService.getCarData(carIndex);
	}
	
	//	특정 위치에 있는 차량 개수 가져오기
	@RequestMapping(value = "/teamcarsharing/api/GetCarCountFromLocation", method = RequestMethod.GET)
	public int getCarCountFromLocation(@RequestParam("location") String location) throws Exception {
		return carService.getCarCountFromLocation(location);
	}
	
	//	특정 위치에 있는 차량 데이터 가져오기
	@RequestMapping(value = "/teamcarsharing/api/GetCarFromLocation", method = RequestMethod.GET)
	public Object getCarFromLocation(@RequestParam("location") String location) throws Exception {
		return carService.getCarFromLocation(location);
	}
	
	//	특정 위치에 대여 가능한 차량 개수 가져오기
	@RequestMapping(value = "/teamcarsharing/api/GetAllowReserveCarCountFromLocation", method = RequestMethod.GET)
	public int getAllowReserveCarCountFromLocation(@RequestParam("location") String location) throws Exception {
		return carService.getAllowReserveCarCountFromLocation(location);
	}
	
	//	특정 위치에 대여 가능한 차량 가져오기
	@RequestMapping(value = "/teamcarsharing/api/GetAllowReserveCarFromLocation", method = RequestMethod.GET)
	public Object getAllowReserveCarFromLocation(@RequestParam("location") String location) throws Exception {
		return carService.getAllowReserveCarFromLocation(location);
	}
	
	//	중복된 위치를 하나로 묶고 그 위치의 차량 개수 가져오기
	@RequestMapping(value = "/teamcarsharing/api/GetCarCountFromUniqueLocation", method = RequestMethod.GET)
	public Object getCarCountFromUniqueLocation() throws Exception {
		List<UniqueLocationDto> list = carService.getCarCountFromUniqueLocation();
		return list;
	}
}
