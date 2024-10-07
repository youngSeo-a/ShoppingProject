package project.web.code.service.conner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.dto.AuthInfoDTO;
import project.web.code.dto.CartDTO;
import project.web.code.dto.MemberDTO;
import project.web.code.mapper.cart.CartMapper;
import project.web.code.mapper.member.MemberMyMapper;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class CartInsertService {
    private final MemberMyMapper memberMyMapper;
    private final CartMapper cartMapper;

    public String execute(String goodsNum, Integer qty, HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        // 로그인 정보가 있는지 확인.
        if(auth != null) {
            if(auth.getGrade().equals("mem")) {
                MemberDTO.Response memDto = memberMyMapper.memberInfo(auth.getUserId());
                //장바구니 dto에 정보를 저장합니다.
                CartDTO dto = new CartDTO();
                dto.setCartQty(qty);
                dto.setGoodsNum(goodsNum);
                dto.setMemberNum(memDto.getMemberNum());
                cartMapper.cartInsert(dto);
                return "200"; //정상처리됨
            }else {
                System.out.println("직원은 직원전용페이지를 사용하세요..");
                return "999";
            }
        }else {
            System.out.println("로그인을 해야합니다.");
            return "000";
        }
    }
}