package project.web.code.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;



public class MemberDTO {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Response {
        private String memberNum;
        private String memberId;
        private String memberPw;
        private String memberName;
        private String memberAddr;
        private String memberAddrDetail;
        private String memberPost;
        private Date memberRegistDate;
        private String memberPhone;
        private String memberEmail;
        private Date memberBirth;
        private Integer memberPoint;
    }
    @Data
    public static class DateResponse {
        private List<Response> memList;
        private int total;
        private String pageHTML;
        private int pageNum;

    }
}


