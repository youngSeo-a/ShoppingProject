package project.web.code.service.conner;

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
public class CartListService {
    private final MemberMyMapper memberMyMapper;
    private final CartMapper cartMapper;
    public void execute(Model model, HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        MemberDTO.Response memDto = memberMyMapper.memberInfo(auth.getUserId());
        // 상품정보와 장바구니 정보를 같이 가져와야 한다.
        // 상품 한개가 아니라 여러개 이므로 list로 받아야함
        List<CartGoodsDTO> list = cartMapper.cartList(memDto.getMemberNum(), null);
        // 장바구니에 있는 상품전체의 합계금액을 가지고 오겠음.
        Integer sumPrice = cartMapper.sumPrice(memDto.getMemberNum());
        model.addAttribute("list", list);
        model.addAttribute("sumPrice", sumPrice);
        System.out.println("list.size() : " + list.size());
    }
}
