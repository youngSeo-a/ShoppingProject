package project.web.code.service.inquire;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import project.web.code.dto.AuthInfoDTO;
import project.web.code.dto.InquireCommand;
import project.web.code.dto.InquireDTO;
import project.web.code.dto.MemberDTO;
import project.web.code.mapper.inquire.InquireMapper;
import project.web.code.mapper.member.MemberMyMapper;
import project.web.code.repository.InquireRepository;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class InquireWriteService {

    private final MemberMyMapper memberMyMapper;
    private final InquireMapper inquireMapper;

    public void execute(InquireCommand inquireCommand, HttpSession session
            , BindingResult result) {
        // 정해 놓은 오류 코드를 이용해서 오류 메시지를 전달.
        if(inquireCommand.getInquireSubject()=="") {
            result.rejectValue("inquireSubject","wrong");
            return;
        }
        if(inquireCommand.getInquireContent()=="") {
            result.rejectValue("inquireContent","bad");
            return;
        }
        AuthInfoDTO auth = (AuthInfoDTO	)session.getAttribute("auth");
        MemberDTO.Response memDto = memberMyMapper.memberInfo(auth.getUserId());
        InquireDTO dto = new InquireDTO();
        dto.setGoodsNum(inquireCommand.getGoodsNum());
        dto.setInquireContent(inquireCommand.getInquireContent());
        dto.setInquireKind(inquireCommand.getInquireKind());
        dto.setInquireSubject(inquireCommand.getInquireSubject());
        dto.setMemberNum(memDto.getMemberNum());
        inquireMapper.inquireInsert(dto);
    }
}
