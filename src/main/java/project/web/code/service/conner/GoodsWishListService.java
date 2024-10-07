package project.web.code.service.conner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.web.code.dto.AuthInfoDTO;
import project.web.code.dto.GoodsDTO;
import project.web.code.dto.MemberDTO;
import project.web.code.mapper.goods.GoodsMapper;
import project.web.code.mapper.member.MemberMyMapper;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsWishListService {
    private final MemberMyMapper memberMyMapper;
    private final GoodsMapper goodsMapper;

    public void execute(HttpSession session, Model model) {
        // 내 정보를 가져오기 위한 코드
        AuthInfoDTO authInfo = (AuthInfoDTO) session.getAttribute("auth");
        MemberDTO.Response memDto = memberMyMapper.memberInfo(authInfo.getUserId());
        ///관심상품 정보 가지고 오기
        List<GoodsDTO.Response> list = goodsMapper.wishGoodsList(memDto.getMemberNum());
        model.addAttribute("dtos", list);
    }
}
