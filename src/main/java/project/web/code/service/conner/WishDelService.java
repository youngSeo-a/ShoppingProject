package project.web.code.service.conner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.dto.AuthInfoDTO;
import project.web.code.dto.MemberDTO;
import project.web.code.mapper.member.MemberMyMapper;
import project.web.code.mapper.wish.WishMapper;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class WishDelService {

    private final MemberMyMapper memberMyMapper;
    private final WishMapper wishMapper;
    public void execute(String goodsNum, HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        MemberDTO.Response dto = memberMyMapper.memberInfo(auth.getUserId());
        // 관심상품 등록할 때 이미 만들었습니다.
        wishMapper.wishDelete(goodsNum, dto.getMemberNum());
    }
}