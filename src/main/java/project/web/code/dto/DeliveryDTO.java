package project.web.code.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DeliveryDTO {
    String purchaseNum;
    String deliveryNumber;
    Date deliveryDate;
    String deliveryState;
    String deliveryCompany;
}