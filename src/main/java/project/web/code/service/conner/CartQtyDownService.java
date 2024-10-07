package project.web.code.service.conner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.dto.AuthInfoDTO;
import project.web.code.dto.CartGoodsDTO;
import project.web.code.dto.MemberDTO;
import project.web.code.mapper.cart.CartMapper;
import project.web.code.mapper.member.MemberMyMapper;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class CartQtyDownService {

    private final MemberMyMapper memberMyMapper;
    private final CartMapper cartMapper;

    public CartGoodsDTO execute(String goodsNum, HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        MemberDTO.Response memdto =  memberMyMapper.memberInfo(auth.getUserId());
        cartMapper.cartQtyDown(goodsNum, memdto.getMemberNum());
        // 꼭 이코드를 사용하지 않지 않아도 되지만 ObjectMapper를 사용하는 방법과
        // 조인문 없이 조인 하는 방법을 설명하고자 한다.
        CartGoodsDTO dto =  cartMapper.goodsPriceMulCartQty(goodsNum, memdto.getMemberNum());
        System.out.println(dto);
        return dto;
    }
}
