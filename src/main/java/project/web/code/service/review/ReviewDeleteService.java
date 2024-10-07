package project.web.code.service.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.mapper.review.ReviewMapper;

@Service
@RequiredArgsConstructor
public class ReviewDeleteService {
    private final ReviewMapper reviewMapper;
    public void execute(String reviewNum) {
        reviewMapper.reviewDelete(Integer.parseInt(reviewNum));
    }
}
