package project.web.code.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsIpgoDTO {
    String ipgoNum;
    String goodsNum;
    Integer ipgoQty;
    Date ipgoDate;
    Date madeDate;
    Integer ipgoPrice;
    String empNum;
}