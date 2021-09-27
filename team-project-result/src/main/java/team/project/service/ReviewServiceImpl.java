package team.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.project.dto.ReviewDto;
import team.project.mapper.ReviewMapper;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewMapper reviewMapper;
	
	@Override
	public List<ReviewDto> getReview(int carIndex) throws Exception {
		return reviewMapper.getReview(carIndex);
	}

}
