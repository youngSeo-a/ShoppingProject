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
public class WishGoodsDelsService {
    
   private final MemberMyMapper memberMyMapper;
    private final WishMapper wishMapper;

    public void execute(String wishGoodsDels[], HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        MemberDTO.Response dto = memberMyMapper.memberInfo(auth.getUserId());
        wishMapper.wishGoodsDeletes(wishGoodsDels, dto.getMemberNum());
    }
}