package project.web.code.service.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.dto.ReviewDTO;
import project.web.code.mapper.review.ReviewMapper;

@Service
@RequiredArgsConstructor
public class ReviewUpdateService {
    private final ReviewMapper reviewMapper;

    public void execute(Integer reviewNum,String reviewContent) {
        ReviewDTO dto = new ReviewDTO();
        dto.setReviewNum(reviewNum);
        dto.setReviewContent(reviewContent);
        reviewMapper.reviewUpdate(dto);
    }
}
