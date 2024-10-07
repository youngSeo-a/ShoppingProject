package project.web.code.dto;

import lombok.Data;

@Data
public class AuthInfoDTO {
    String userId;
    String userPw;
    String userName;
    String grade;
    String userEmail;
    String userEmailCheck;

    String tableName;
    String pwColumName;
    String userIdColumName;
}
