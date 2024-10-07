package project.web.code.service.memberRegister;

import lombok.RequiredArgsConstructor;
import project.web.code.dto.MemberCommand;
import project.web.code.dto.MemberDTO;
import project.web.code.mapper.member.UserMapper;
import project.web.code.service.EmailSendService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserWriteService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final EmailSendService emailSendService;

    public void execute(Model model, MemberCommand memberCommand){
        MemberDTO.Response dto= null;
        dto= MemberDTO.Response
                .builder()
                .memberNum(memberCommand.getMemberNum())
                .memberAddr(memberCommand.getMemberAddr())
                .memberAddrDetail(memberCommand.getMemberAddr2())
                .memberBirth(memberCommand.getMemberBirth())
                .memberEmail(memberCommand.getMemberEmail())
                .memberId(memberCommand.getMemberId())
                .memberName(memberCommand.getMemberName())
                .memberPhone(memberCommand.getMemberPhone())
                .memberPost(memberCommand.getMemberPost())
                //비밀번호를 암호화.
                .memberPw(passwordEncoder.encode(memberCommand.getMemberPw()))
                .build();
        int i = userMapper.userInsert(dto);
        model.addAttribute("userName", dto.getMemberName());
        model.addAttribute("userEmail", dto.getMemberEmail());

        if(i >= 1) {
            /// 메일링
            String html= "<html><body>"
                    + "    땡떙땡님 회원 가입을 축하합니다. <br />"
                    + " 가입을 완료하시려면 "
                    + "<a href='http://localhost:9090/userConfirm?chk=" + dto.getMemberEmail()
                    + "'>여기</a>"
                    + "를 눌러주세요";
            String subject = "환영 인사입니다.";
            String fromEmail = "ybh4953@gmail.com"; // 보내는 사람
            String toEmail = dto.getMemberEmail(); // 받는 사람

            emailSendService.mailsend(html, subject, fromEmail, toEmail);
            // 이메일이 전송이 될것이다. 이제 가입확인을 할 수 있는 userConfirm를 만들어야 한다.
        }
    }
}
