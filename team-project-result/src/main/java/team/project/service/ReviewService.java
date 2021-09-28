package team.project.service;

import java.util.List;

import team.project.dto.ReviewDto;

public interface ReviewService {
	List<ReviewDto> getReview(int carIndex) throws Exception;
}
