package project.web.code.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.dto.MemberCommand;
import project.web.code.dto.MemberDTO;
import project.web.code.mapper.member.MemberMapper;

@Service
@RequiredArgsConstructor
public class MemberUpdateService {
   private final MemberMapper memberMapper;
    public void execute(MemberCommand memberCommand) {
        MemberDTO.Response dto = new MemberDTO.Response();

        dto.setMemberAddr(memberCommand.getMemberAddr());
        dto.setMemberAddrDetail(memberCommand.getMemberAddr2());
        dto.setMemberBirth(memberCommand.getMemberBirth());
        dto.setMemberEmail(memberCommand.getMemberEmail());
        dto.setMemberName(memberCommand.getMemberName());
        dto.setMemberNum(memberCommand.getMemberNum());
        dto.setMemberPhone(memberCommand.getMemberPhone());
        dto.setMemberPost(memberCommand.getMemberPost());
        dto.setMemberPoint(memberCommand.getMemberPoint());
        memberMapper.memberUpdate(dto);
    }
}
