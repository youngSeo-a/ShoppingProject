package project.web.code.service.conner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.dto.AuthInfoDTO;
import project.web.code.dto.MemberDTO;
import project.web.code.mapper.cart.CartMapper;
import project.web.code.mapper.member.MemberMyMapper;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class GoodsCartDelService {

    private final MemberMyMapper memberMyMapper;
    private final CartMapper cartMapper;

    public void execute(String goodsNum , HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        MemberDTO.Response dto = memberMyMapper.memberInfo(auth.getUserId());
        cartMapper.goodsNumDelete(goodsNum, dto.getMemberNum());
    }
}