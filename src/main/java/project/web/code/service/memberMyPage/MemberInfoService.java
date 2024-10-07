package project.web.code.service.memberMyPage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.web.code.dto.AuthInfoDTO;
import project.web.code.dto.MemberCommand;
import project.web.code.dto.MemberDTO;
import project.web.code.mapper.member.MemberMyMapper;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class MemberInfoService {

    private final MemberMyMapper memberMyMapper;
    public void execute(HttpSession session, Model model) {
        //로그인 할 때 만든 session
        AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("auth");
        String memberId = authInfo.getUserId();
        // 아이디를 이용해서 회원정보를 가지고 옵니다.
        MemberDTO.Response dto = memberMyMapper.memberInfo(memberId);
        //dto를 html 에 보내기 위해 model에 저장합니다.
        // 단 나중에 command로 사용해야 할 수도 있어서 comamnd에 dto를 옮기도록 합니다.
        // 만약 dto와 command의 멤버필드가 같다면 dto를 model에 저장해도 됩니다.
        // 여기서는 dto와 command의 멤버필드가  몇몇개가 달라 command에 저장하도록 합니다.
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
