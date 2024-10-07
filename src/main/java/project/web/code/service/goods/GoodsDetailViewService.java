package project.web.code.service.goods;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.web.code.dto.AuthInfoDTO;
import project.web.code.dto.GoodsDTO;
import project.web.code.dto.GoodsDetailStockDTO;
import project.web.code.dto.MemberDTO;
import project.web.code.mapper.goods.GoodsMapper;
import project.web.code.mapper.member.MemberMyMapper;
import project.web.code.mapper.wish.WishMapper;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class GoodsDetailViewService {

    private final GoodsMapper goodsMapper;
    private final MemberMyMapper memberMyMapper;
    private final WishMapper wishMapper;

    public void execute(String goodsNum, Model model, HttpSession session) {
        // detailView.html에 들어 갈 데이터를 가지고 옵니다.
        goodsMapper.visitCount(goodsNum); //방문자수 증가는 상품을 가져오기전에 증가를 시켜야 한다.
        GoodsDetailStockDTO dto = goodsMapper.goodsDetailStock(goodsNum);
        //회원정보를 가지고 옵니다.
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        if (auth != null) { //로그인 정보가 있다면
            try { //직원인 경우 오류가 발생할 수 있으므로 예외처리를 해준다.
                MemberDTO.Response memdto = memberMyMapper.memberInfo(auth.getUserId());
                // 회원이 등록한 상품이 있는 지 확인하기 위해서 조금전에 만들었다.
                Integer i = wishMapper.wishGoodsSelect(goodsNum, memdto.getMemberNum());
                model.addAttribute("wish", i);
            }catch(Exception e) {}
        }
        model.addAttribute("dto", dto);
    }
}