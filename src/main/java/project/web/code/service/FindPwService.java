package project.web.code.service;

import lombok.RequiredArgsConstructor;
import project.web.code.dto.AuthInfoDTO;
import project.web.code.mapper.find.FindMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindPwService {

    private final FindMapper findMapper;
    private final PasswordEncoder passwordEncoder; //암호화 시키고
    private final EmailSendService emailSendService; //메일을 통해 임시비밀번호 발송

    public void execute(String userId, String userPhone, Model model) {
        AuthInfoDTO au = new AuthInfoDTO();
        AuthInfoDTO dto = findMapper.userEmail(userId, userPhone);
        if (dto != null) {
            String newPw = UUID.randomUUID().toString().substring(0, 8);
            dto.setUserId(userId);
            // 새 비밀번호를 암호화 시킨다.
            dto.setUserPw(passwordEncoder.encode(newPw));
            if(dto.getGrade().equals("mem")) { //회원인 경우
                dto.setTableName("member_info");
                dto.setPwColumName("member_pw");
                dto.setUserIdColumName("member_id");
            }else { // 직원인 경우
                dto.setTableName("emp_info");
                dto.setPwColumName("emp_pw");
                dto.setUserIdColumName("emp_id");
            }
            findMapper.pwUpdate(dto);
            model.addAttribute("dto", dto);
            String html= "<html><body>"
                    + dto.getUserEmail() + "님의 임시 비밀번호는 " + newPw
                    + "입니다. </body></html>";
            String subject = dto.getUserEmail()+"의 임시비밀번호";
            String fromEmail = "ybh4953@gmail.com";
            String toEmail = dto.getUserEmail();
            emailSendService.mailsend(html, subject, fromEmail, toEmail);
        }
    }
}
