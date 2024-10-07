package project.web.code.dto;

import lombok.Data;

import java.util.Date;

@Data
public class InquireDTO {
    Integer inquireNum;
    String memberNum;
    String goodsNum;
    String inquireKind;
    String inquireSubject;
    String inquireContent;
    Date inquireDate;
    String inquireAnswer; // 답변
    Date inquireAnswerDate;
    String empNum;

    String memberId;
    String goodsName;
}