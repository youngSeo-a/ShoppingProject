package project.web.code.service.purchase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.web.code.dto.OrderListDTO;
import project.web.code.mapper.purchase.PurchaseMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseListService {

  private final PurchaseMapper purchaseMapper;
    public void execute(Model model) {
        //회원이 구매리스트를 가져왔을 때의 로직을 그대로 사용하면 됩니다.
        List<OrderListDTO> list = purchaseMapper.orderList(null, null);
        model.addAttribute("list", list);
    }
}