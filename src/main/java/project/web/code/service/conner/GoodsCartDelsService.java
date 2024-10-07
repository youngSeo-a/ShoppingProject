package project.web.code.service.conner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.dto.AuthInfoDTO;
import project.web.code.dto.MemberDTO;
import project.web.code.mapper.cart.CartMapper;
import project.web.code.mapper.member.MemberMyMapper;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GoodsCartDelsService {
    private final MemberMyMapper memberMyMapper;
    private final CartMapper cartMapper;

    public String execute(String[] goodsNums ,  HttpSession session) {
        //회원 정보 가져오기
        AuthInfoDTO auth  = (AuthInfoDTO)session.getAttribute("auth");
        MemberDTO.Response memDto = memberMyMapper.memberInfo(auth.getUserId());
        // 이번에는 배열을 그냥 넘기는 것이 아니라 map에 저장해서 넘겨보겠습니다.
        List<String> cs  = new ArrayList<String>();
        // 배열에 있는 값을 리스트에 저장하겠습니다.
        for(String goodsNum : goodsNums) {
            cs.add(goodsNum);
        }
        //리스트에 저장한 값을 리스트에 넣겠습니다.
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("goodsNums", cs);
        // map에 회원번호도 넣겠습니다.
        condition.put("memberNum", memDto.getMemberNum());
        int i = cartMapper.goodsNumsDelete(condition);
        if (i >= 1) {return "200";}
        else return "999";
    }
}
