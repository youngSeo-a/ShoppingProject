package project.web.code.service.member;

import lombok.RequiredArgsConstructor;
import project.web.code.dto.MemberCommand;
import project.web.code.mapper.member.MemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class MemberAutoNumService {
  private final MemberMapper memberMapper;
    public void execute(Model model) {
        String memberNum = memberMapper.memberAutoNum();
        MemberCommand dto = new MemberCommand();
        dto.setMemberNum(memberNum);
        model.addAttribute("memberCommand", dto);
    }
}
