package project.web.code.service.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.web.code.dto.ReviewDTO;
import project.web.code.mapper.review.ReviewMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewListService {

    private final ReviewMapper reviewMapper;
    public void execute(String goodsNum, Model model) {
        List<ReviewDTO> list =  reviewMapper.goodsReviewList(goodsNum);
        model.addAttribute("list", list);
    }
}