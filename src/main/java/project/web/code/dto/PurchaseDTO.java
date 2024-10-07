package project.web.code.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseDTO {
    String purchaseNum;
    Date purchaseDate;
    Integer purchasePrice;
    String deliveryAddr;
    String deliveryAddrDetail;
    String deliveryPost;
    String deliveryPhone;
    String message;
    String purchaseStatus;
    String memberNum;
    String deliveryName;
}