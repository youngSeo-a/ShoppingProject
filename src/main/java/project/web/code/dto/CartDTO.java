package project.web.code.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CartDTO {
    Integer cartNum;
    String memberNum;
    String goodsNum;
    Integer cartQty;
    Date cartDate;
    String [] goodsNums;
}