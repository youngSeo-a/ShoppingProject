package project.web.code.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class GoodsIpgoCommand {
    String goodsIpgoNum;
    String goodsNum;
    Integer ipgoQty;
    Integer ipgoPrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date ipgoDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime madeDate;
}