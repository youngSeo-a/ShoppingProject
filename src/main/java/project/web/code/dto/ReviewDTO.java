package project.web.code.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewDTO {
    String goodsNum;
    String purchaseNum;
    Date reviewDate;
    String reviewContent;

    Integer score;
    Integer reviewNum;
    String memberId;
}