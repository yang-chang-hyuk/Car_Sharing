package team.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import team.project.dto.CarReserveDto;
import team.project.service.CarReserveService;

@RestController
public class CarReserveRestAPIController {
	@Autowired
	private CarReserveService carReserveService;
	
	/**
	 * 차량 인덱스 번호로 대여 정보 가져오기
	 * @param carIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/teamcarsharing/api/GetCarReservedData", method = RequestMethod.GET)
	public Object getCarReservedData(@RequestParam("carIndex") int carIndex) throws Exception {
		return carReserveService.getCarReservedData(carIndex);
	}
	
	/**
	 * 해당 차량 인덱스가 대여 상태인지 여부 확인하기
	 * @param carIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/teamcarsharing/api/IsCarReserved", method = RequestMethod.GET)
	public int isCarReserved(@RequestParam("carIndex") int carIndex) throws Exception {
		return carReserveService.isCarReserved(carIndex);
	}
	
	/**
	 * 해당 사용자의 대여 정보 가져오기
	 * @param userIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/teamcarsharing/api/GetUserCarReservedData", method = RequestMethod.GET)
	public Object getUserCarReservedData(@RequestParam("userIndex") int userIndex) throws Exception {
		return carReserveService.getUserCarReservedData(userIndex);
	}
	
	/**
	 * 해당 사용자가 대여 상태인지 여부 확인하기
	 * @param userIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/teamcarsharing/api/IsUserCarReserved", method = RequestMethod.GET)
	public int isUserCarReserved(@RequestParam("userIndex") int userIndex) throws Exception {
		return carReserveService.isUserCarReserved(userIndex);
	}
	
	/**
	 * 해당 위치의 대여한 차량 정보 목록 가져오기
	 * @param location
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/teamcarsharing/api/GetReserveCarFromLocation", method = RequestMethod.GET)
	public Object getReserveCarFromLocation(@RequestParam("location") String location) throws Exception {
		return carReserveService.getReserveCarFromLocation(location);
	}
	
	@RequestMapping(value = "/teamcarsharing/api/CarReserve", method = RequestMethod.POST)
	public void carReserve(@RequestBody CarReserveDto carReserve) throws Exception {
		carReserveService.carReserve(carReserve);
	}
	
	/**
	 * 유저 대여 차량 삭제하기
	 * @param userIndex
	 * @throws Exception
	 */
	@RequestMapping(value = "/teamcarsharing/api/DeleteUserReserveCar", method = RequestMethod.DELETE)
	public void deleteUserReserveCar(@RequestParam("userIndex") int userIndex) throws Exception {
		carReserveService.deleteUserReserveCar(userIndex);
	}
	
}
