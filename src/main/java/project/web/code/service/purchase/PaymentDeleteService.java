package project.web.code.service.purchase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.mapper.purchase.PurchaseMapper;

@Service
@RequiredArgsConstructor
public class PaymentDeleteService {
   private final PurchaseMapper purchaseMapper;

    public void execute(String purchaseNum) {
        int i = purchaseMapper.paymentDelete(purchaseNum);
        if(i >= 1) {
            purchaseMapper.purchaseStatusUpdate("입금대기중" ,purchaseNum);
        }
    }
}
