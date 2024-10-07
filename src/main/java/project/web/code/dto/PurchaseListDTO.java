package project.web.code.dto;

import lombok.Data;

@Data
public class PurchaseListDTO {
    String purchaseNum;
    String  goodsNum;
    Integer purchaseQty;
    Integer totalPrice;

    String memberNum;
    String [] goodsNums;
}
