package project.web.code.service.purchase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.web.code.dto.AuthInfoDTO;
import project.web.code.dto.MemberDTO;
import project.web.code.dto.OrderListDTO;
import project.web.code.mapper.member.MemberMyMapper;
import project.web.code.mapper.purchase.PurchaseMapper;

import javax.servlet.http.HttpSession;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderProcessListService {
    private final MemberMyMapper memberMyMapper;
    private final PurchaseMapper purchaseMapper;

    public void execute(HttpSession session, Model model) {
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        MemberDTO.Response memDto = memberMyMapper.memberInfo(auth.getUserId());
        List<OrderListDTO> list = purchaseMapper.orderList(memDto.getMemberNum(), null);
        model.addAttribute("list", list);
    }
}
