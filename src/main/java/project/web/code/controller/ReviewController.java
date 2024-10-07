package project.web.code.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.web.code.dto.ReviewDTO;
import project.web.code.service.goods.GoodsDetailService;
import project.web.code.service.review.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final GoodsDetailService goodsDetailService;
    private final ReviewWriteService reviewWriteService;
    private final ReviewDetailService reviewDetailService;
    private final ReviewUpdateService reviewUpdateService;
    private final ReviewListService reviewListService;
    private final ReviewDeleteService reviewDeleteService ;

    @PostMapping("/reviewList")
    public String reviewList(
            @RequestParam("goodsNum") String goodsNum,
            Model model) {
        reviewListService.execute(goodsNum, model);
        model.addAttribute("newLineChar", "\n");
        return "views/review/reviewList";
    }

    @PostMapping("/goodsReviewModify")
    public String goodsReviewUpdate(
            @RequestParam(value="reviewNum") Integer reviewNum,
            @RequestParam(value="reviewContent") String reviewContent
    ) {
        reviewUpdateService.execute(reviewNum, reviewContent);
        return "redirect:/purchase/orderList";
    }

    @PostMapping("/goodsReviewUpdate")
    public void goodsReviewUpdate(
            @RequestParam(value="reviewNum") Integer reviewNum
            , HttpServletResponse response) { //데이터를 json형식으로 넘기기 위해 response가 필요..
        ReviewDTO dto = reviewDetailService.execute(reviewNum);
        //날짜를 원하는 형식으로 변경한다.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String reviewDate = sdf.format(dto.getReviewDate());
        response.setCharacterEncoding("utf-8"); // json형식으로 데이터를 적어준다.
        String reviewJson   = "{\"reviewNum\":\"" + dto.getReviewNum() + "\"";
        reviewJson  += " ,\"reviewContent\":\"" + dto.getReviewContent() + "\"";
        reviewJson  += " ,\"reviewDate\":\"" + reviewDate + "\"}";
        try {
            response.getWriter().print(reviewJson); // ajax에 json형식으로 전달
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @GetMapping("/goodsReviewUpdate")
    public String goodsReviewUpdate(
            @ModelAttribute(value="reviewNum") String reviewNum,
            @RequestParam(value="goodsNum") String goodsNum,
            Model model) {
        goodsDetailService.execute(goodsNum, model);
        return "views/review/goodsReviewUpdate";
    }

    @PostMapping("/reviewWrite")
    public String reviewWrite(
            @RequestParam(value="goodsNum") String goodsNum,
            @RequestParam(value="reviewContent") String reviewContent,
            @RequestParam(value="purchaseNum") String purchaseNum,
            HttpSession session) {
        reviewWriteService.execute(goodsNum,reviewContent,purchaseNum,session );
        return "redirect:/purchase/orderList";
    }

    @GetMapping("/goodsReview")
    public String goodsReview(
            @ModelAttribute("goodsNum") String goodsNum, // requestpara로 받아 model에 저장합니다.
            @ModelAttribute("purchaseNum")String purchaseNum
            ,Model model){
        // 리뷰를 쓰기 위해 제품 정보를 가져오겠습니다.
        goodsDetailService.execute(goodsNum, model);
        return "views/review/goodsReview";
    }
    @GetMapping("/goodsReviewDelete")
    public String goodsReviewDelete(
            @RequestParam(value="reviewNum") String reviewNum) {
        reviewDeleteService.execute(reviewNum);
        return "redirect:/purchase/orderList";
    }
}