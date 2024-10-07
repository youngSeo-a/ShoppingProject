package project.web.code.mapper.review;

import org.apache.ibatis.annotations.Mapper;
import project.web.code.dto.ReviewDTO;

import java.util.List;
@Mapper
public interface ReviewMapper {
    public Integer reviewWrite(ReviewDTO dto);
    public ReviewDTO reviewSelect(Integer reviewNum);
    public Integer reviewUpdate(ReviewDTO dto);
    public Integer reviewDelete(Integer reviewNum);
    public List<ReviewDTO> goodsReviewList(String goodsNum);
}
