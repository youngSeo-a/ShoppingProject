package project.web.code.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;


public class EmployeeDTO {
    @Data
    @Builder
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class Response {
    private String empNum;
    private String empId;
    private String empPw;
    private String empName;
    private String empAddr;
    private String empAddrDetail;
    private String empPost;
    private String empPhone;
    private String empEmail;
    private Date empRegiDate;
    private String empJumin;
    }
    @Data
    public static class DateResponse {
        private List<EmployeeDTO.Response> empList;
        private int total;
        private String pageHTML;
        private int pageNum;

    }
}