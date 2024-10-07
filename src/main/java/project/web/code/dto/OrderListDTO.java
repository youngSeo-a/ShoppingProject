package project.web.code.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderListDTO {
    // 구매정보 마다 결제정보 하나를 가지고 온다.
    PurchaseDTO purchaseDTO; // 1
    PaymentDTO paymentDTO;   //1
    //배송정보를 추가해준다.
    DeliveryDTO DeliveryDTO;  //이제 mapper에도 추가한다.
    // 구매정보에 따른 하나 이상의 구매정보와 상품정보를 가져와야 하므로 list로 받아온다.
    List<PurchaseListGoodsDTO> purchaseListGoodsDTOs; // n
}