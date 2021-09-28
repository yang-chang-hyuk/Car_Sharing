package team.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import team.project.dto.ReviewDto;
import team.project.service.ReviewService;

@RestController
public class ReviewRestAPIController {
	@Autowired
	ReviewService reviewService;
	
	/**
	 * 리뷰 가져오기
	 * @param carIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/api/GetReview", method=RequestMethod.GET)
	public List<ReviewDto> getReview(@RequestParam("carIndex")int carIndex) throws Exception{
		return reviewService.getReview(carIndex);
	}
}
