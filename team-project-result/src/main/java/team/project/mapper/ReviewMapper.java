package team.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import team.project.dto.ReviewDto;

@Mapper
public interface ReviewMapper {
	List<ReviewDto> getReview(int carIndex) throws Exception;

}
