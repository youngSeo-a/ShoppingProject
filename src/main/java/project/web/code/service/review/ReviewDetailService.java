package project.web.code.service.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.dto.ReviewDTO;
import project.web.code.mapper.review.ReviewMapper;


@Service
@RequiredArgsConstructor
public class ReviewDetailService {

    private final ReviewMapper reviewMapper;
    public ReviewDTO execute(Integer reviewNum) {
        ReviewDTO dto = reviewMapper.reviewSelect(reviewNum);
        return dto;
    }
}
