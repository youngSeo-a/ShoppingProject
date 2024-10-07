package project.web.code.service.memberMyPage;

import lombok.RequiredArgsConstructor;
import project.web.code.dto.AuthInfoDTO;
import project.web.code.dto.MemberCommand;
import project.web.code.dto.MemberDTO;
import project.web.code.mapper.member.MemberMyMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class MemberInfoUpdateService {
    private final PasswordEncoder passwordEncoder;
    private final MemberMyMapper memberMyMapper;

    public int execute(MemberCommand memberCommand, HttpSession session, BindingResult result) {
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        if (!passwordEncoder.matches(memberCommand.getMemberPw(), auth.getUserPw())) {
            result.rejectValue("memberPw", "memberCommand.memberPw", "비밀번호가 틀렸습니다.");
            return 0;
        } else {
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
            memberMyMapper.memberInfoUpdate(dto);
            return 1;
        }
    }
}