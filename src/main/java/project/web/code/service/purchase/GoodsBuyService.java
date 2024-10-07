package project.web.code.service.purchase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.web.code.dto.AuthInfoDTO;
import project.web.code.dto.CartGoodsDTO;
import project.web.code.dto.MemberDTO;
import project.web.code.mapper.cart.CartMapper;
import project.web.code.mapper.member.MemberMyMapper;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsBuyService {

    private final MemberMyMapper memberMyMapper;
    private final CartMapper cartMapper;

    public void execute(String [] prodCk, HttpSession session, Model model) {
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        MemberDTO.Response memDto = memberMyMapper.memberInfo(auth.getUserId());
        //카트로 부터 구매정보를 가지고와야 합니다. // 그런데 문제는 prodCk네 있는 goodsNum만 가지고 와야 합니다.
        List<CartGoodsDTO> list = cartMapper.cartList(memDto.getMemberNum(), prodCk);
        Integer sumPrice = 0; // 상품수량 합계금액
        Integer sumTotalPrice = 0; // 결제 금액
        Integer sumDeliveryCost = 0; // 배송비합계금액
        String goodsNums = ""; // 상품번호들 저장
        for(CartGoodsDTO dto : list) {
            sumTotalPrice += dto.getTotalPrice();
            sumDeliveryCost += dto.getGoodsDTO().getDeliveryCost();
            goodsNums += dto.getGoodsDTO().getGoodsNum() + "-";
        }
        sumPrice = sumTotalPrice + sumDeliveryCost;
        model.addAttribute("list", list);
        model.addAttribute("sumPrice", sumPrice);
        model.addAttribute("sumTotalPrice", sumTotalPrice);
        model.addAttribute("sumDeliveryCost", sumDeliveryCost);
        model.addAttribute("goodsNums", goodsNums);
    }
}