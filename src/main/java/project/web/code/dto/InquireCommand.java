package project.web.code.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class InquireCommand {
    Integer inquireNum;
    String goodsNum;
    String inquireKind;
    String inquireSubject;
    String inquireContent;
    String inquireAnswer;
    String memberNum;
}