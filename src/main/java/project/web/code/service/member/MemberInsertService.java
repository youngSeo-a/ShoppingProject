package project.web.code.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.web.code.dto.MemberCommand;
import project.web.code.dto.MemberDTO;
import project.web.code.mapper.member.MemberMapper;

@Service
@RequiredArgsConstructor
public class MemberInsertService {

    private final PasswordEncoder passwordEncoder;
    private final MemberMapper memberMapper;

    public void execute(MemberCommand memberCommand){
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
        memberMapper.memberInsert(dto);
    }
}
