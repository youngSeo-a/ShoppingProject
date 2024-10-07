package project.web.code.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


public class GoodsDTO {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Response {
        private String goodsNum;
        private String goodsName;
        private Integer goodsPrice;
        private Integer deliveryCost;
        private String goodsContent;
        private String empNum;

        private Integer visitCount;
        private Date goodsRegist;
        private String updateEmpNum;
        private Date goodsUpdateDate;

        // 디비에 파일명을 저장하기 위해 주가합니다.
        private String goodsMainStore;
        private String goodsMainStoreImg;
        private String goodsImages;
        private String goodsImagesImg;
    }

    @Data
    public static class DateResponse {
        private List<GoodsDTO.Response> goodsList;
        private int total;
        private String pageHTML;
        private int pageNum;
    }
}
