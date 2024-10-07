package project.web.code.service.memberMyPage;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.web.code.dto.AuthInfoDTO;
import project.web.code.mapper.member.MemberMyMapper;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class MemberDropService {

    private final  PasswordEncoder passwordEncoder;
    private final MemberMyMapper memberMyMapper;

    public int execute(String memberPw, HttpSession session, Model model) {
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        if(passwordEncoder.matches(memberPw, auth.getUserPw())) {
            int i = memberMyMapper.memberDrop(auth.getUserId());
            return 1;
        }else {
            model.addAttribute("errPw", "비밀번호가 틀렸습니다.");
            return 0;
        }
    }
}