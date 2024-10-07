package project.web.code.dto;

import lombok.Data;

@Data
public class PaymentDTO {
    String purchaseNum;
    String confirmnumber;
    String cardnum;
    String tid;
    String totalprice;
    String resultmessage;
    String paymethod;
    String appldate;
    String appltime;
    String purchasename;
}