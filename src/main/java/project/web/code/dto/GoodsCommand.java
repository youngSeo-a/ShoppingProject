package project.web.code.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class GoodsCommand {
    String goodsNum;
    @NotEmpty(message = "이름을 입력해주세요")
    String goodsName;
    @NotNull(message = "가격을 입력해주세요.")
    Integer goodsPrice;
    @NotNull(message = "배송비를 입력해주세요.")
    Integer deliveryCost;
    @NotEmpty(message = "설명을 입력해주세요")
    String goodsContent;
    //파일을 받을 객체를 추가합니다.
    MultipartFile goodsMainStore;
    MultipartFile goodsImages[];
    // 디비에 있는 컬럼들을 나중에 필요할 수 있어서 추가 합니다.
    Integer visitCount;
    String empNum;
    Date goodsRegist;
    String updateEmpNum;
    Date goodsUpdateDate;

}

