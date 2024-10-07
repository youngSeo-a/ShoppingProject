package project.web.code.service.member;

import lombok.RequiredArgsConstructor;
import project.web.code.dto.MemberCommand;
import project.web.code.dto.MemberDTO;
import project.web.code.mapper.member.MemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class MemberDetailService {

   private final MemberMapper memberMapper;
    public void execute(String memberNum, Model model) {
        MemberDTO.Response dto = memberMapper.memberSelectOne(memberNum);

        MemberCommand memberCommand = new MemberCommand();

        memberCommand.setMemberAddr(dto.getMemberAddr());
        memberCommand.setMemberAddr2(dto.getMemberAddrDetail());
        memberCommand.setMemberBirth(dto.getMemberBirth());
        memberCommand.setMemberEmail(dto.getMemberEmail());
        memberCommand.setMemberId(dto.getMemberId());
        memberCommand.setMemberName(dto.getMemberName());
        memberCommand.setMemberNum(dto.getMemberNum());
        memberCommand.setMemberPhone(dto.getMemberPhone());
        memberCommand.setMemberPost(dto.getMemberPost());
        memberCommand.setMemberRegiDate(dto.getMemberRegistDate());
        memberCommand.setMemberPoint(dto.getMemberPoint());
        model.addAttribute("memberCommand", memberCommand);
    }


}
