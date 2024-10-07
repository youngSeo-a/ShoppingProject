package project.web.code.service.purchase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.mapper.purchase.PurchaseMapper;

@Service
@RequiredArgsConstructor

public class PurchaseStatusService {

    private final PurchaseMapper purchaseMapper;
    public void execute(String purchaseNum) {
        purchaseMapper.purchaseStatusUpdate("상품준비중", purchaseNum);
    }
}