package project.web.code.service.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.dto.AuthInfoDTO;
import project.web.code.dto.ReviewDTO;
import project.web.code.mapper.review.ReviewMapper;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class ReviewWriteService {

    private final ReviewMapper reviewMapper;
    public void execute(String goodsNum, String reviewContent,String purchaseNum,
                        HttpSession session) {
        AuthInfoDTO auth= (AuthInfoDTO)session.getAttribute("auth");
        ReviewDTO dto = new ReviewDTO();
        dto.setGoodsNum(goodsNum);
        dto.setPurchaseNum(purchaseNum);
        dto.setReviewContent(reviewContent);
        dto.setMemberId(auth.getUserId());
        reviewMapper.reviewWrite(dto);
    }
}
