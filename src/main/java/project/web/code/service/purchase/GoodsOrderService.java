package project.web.code.service.purchase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.web.code.dto.*;
import project.web.code.mapper.cart.CartMapper;
import project.web.code.mapper.member.MemberMyMapper;
import project.web.code.mapper.purchase.PurchaseMapper;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class GoodsOrderService {

    private final MemberMyMapper memberMyMapper;
    private final PurchaseMapper purchaseMapper;
    private final CartMapper cartMapper;

    public String execute(PurchaseCommand purchaseCommand, HttpSession session, Model model) {
        String purchaseNum = "";
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        MemberDTO.Response memDto = memberMyMapper.memberInfo(auth.getUserId());
        // 구매 번호를 먼저 구해 오겠습니다.
        purchaseNum = purchaseMapper.selectNum();
        //  command 에 있는 상품번호를 split()해서 배열로 받아온다.
        String [] goodsNums = purchaseCommand.getGoodsNums().split("-");
        // 각각의 정보들을 구매dto에 저장한다.
        PurchaseDTO dto = new PurchaseDTO();
        dto.setPurchaseNum(purchaseNum);
        dto.setDeliveryAddr(purchaseCommand.getDeliveryAddr());
        dto.setDeliveryAddrDetail(purchaseCommand.getDeliveryAddrDetail());
        dto.setDeliveryName(purchaseCommand.getDeliverName());
        dto.setDeliveryPhone(purchaseCommand.getDeliveryPhone());
        dto.setDeliveryPost(purchaseCommand.getDeliveryPost());
        dto.setMemberNum(memDto.getMemberNum());
        dto.setMessage(purchaseCommand.getMessage());
        dto.setPurchasePrice(purchaseCommand.getSumPrice());
        dto.setPurchaseStatus("입금대기중");
        // 구매정보를 데이블에 저장.
        purchaseMapper.purchaseInsert(dto);
        //구매한 상품정보들을 입력.
        PurchaseListDTO plDto = new PurchaseListDTO();
        plDto.setGoodsNums(goodsNums);
        plDto.setPurchaseNum(purchaseNum);
        plDto.setMemberNum(memDto.getMemberNum());
        purchaseMapper.purchaseListInsert(plDto);
        // cart에 있는 정보를 삭제.
        CartDTO cartDto = new CartDTO();
        cartDto.setMemberNum(memDto.getMemberNum());
        cartDto.setGoodsNums(goodsNums);
        cartMapper.cartGoodsDeletes(cartDto);
        return purchaseNum;
    }
}
