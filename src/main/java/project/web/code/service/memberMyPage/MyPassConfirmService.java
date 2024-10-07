package project.web.code.service.memberMyPage;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.web.code.dto.AuthInfoDTO;
import project.web.code.mapper.member.MemberMyMapper;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class MyPassConfirmService {

  private final PasswordEncoder passwordEncoder;
    private final MemberMyMapper memberMyMapper;

    public boolean execute(String newPw, String oldPw, HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        if(passwordEncoder.matches(oldPw, auth.getUserPw())) {
            String  userPw = passwordEncoder.encode(newPw);// 새 비밀번호 암호화하기
            // 암호화된 비밀번호를 디비에저장
            memberMyMapper.memberPwUpdate(userPw, auth.getUserId());
            // 새 비밀번호를 session에 저장
            auth.setUserPw(userPw);
            return true;
        }else return false;
    }
}