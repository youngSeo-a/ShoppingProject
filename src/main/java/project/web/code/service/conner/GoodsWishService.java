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

public class GoodsWishService {
    public final MemberMyMapper memberMyMapper;
    public final WishMapper wishMapper;

    public String execute(String goodsNum, HttpSession session) {
        //로그인 정보
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        //회원번호(memberNum)이 필요한데 로그인 정보에는 memberId가 있음.
        MemberDTO.Response memDto = memberMyMapper.memberInfo(auth.getUserId());
        // 로그인 아이디를 이용해서 회원정보를 가지고 왔음.
        // 관심상품에 등록, 단 관심상품은 한번만 등록하므로 다시 눌렀을 때는 취소가 되어야 하므로
        // 먼저 관심상품이 있는지 확인 하겠음.
        Integer i = wishMapper.wishGoodsSelect(goodsNum, memDto.getMemberNum());
        if(auth.getGrade().equals("mem")) { //mem은 회원, emp는 직원이다.
            if (i == null) {
                // null 이라는 것은 등록된 상품이 없다는 것이므로 등록을 해야 함.
                wishMapper.wishInsert(goodsNum, memDto.getMemberNum());
                return "1";
            }else if(i == 1) {
                // 1이 왔다는 것은 등록된것이 있으므로 취소를 해야 함.
                wishMapper.wishDelete(goodsNum, memDto.getMemberNum());
                return "0";
            }
        }else {
            return "999";
        }
        return null;
    }
}
