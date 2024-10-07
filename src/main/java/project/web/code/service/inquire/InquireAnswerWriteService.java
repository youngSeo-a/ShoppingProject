package project.web.code.service.inquire;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.dto.*;
import project.web.code.mapper.employee.EmployeeMyMapper;
import project.web.code.mapper.inquire.InquireMapper;
import project.web.code.mapper.member.MemberMapper;
import project.web.code.repository.InquireRepository;
import project.web.code.service.EmailSendService;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class InquireAnswerWriteService {

    private final EmployeeMyMapper employeeMyMapper;
    private final InquireMapper inquireMapper;
    private final MemberMapper memberMapper;
    private final EmailSendService emailSendService; // 답변을 메일링합니다.

    public void execute(InquireCommand inquireCommand, HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        EmployeeDTO.Response emp = employeeMyMapper.employeeInfo(auth.getUserId());
        InquireDTO dto = new InquireDTO();
        dto.setInquireNum(inquireCommand.getInquireNum());
        dto.setInquireAnswer(inquireCommand.getInquireAnswer());
        dto.setEmpNum(emp.getEmpNum());
        int i = inquireMapper.inquireAnswerUpdate(dto);
        try {
            if(i > 0 ) { // 답변 메일링
                MemberDTO.Response memDto = memberMapper.memberSelectOne(inquireCommand.getMemberNum());
                String subject = inquireCommand.getInquireSubject() +"의 답변";
                String content = inquireCommand.getInquireSubject() +"의 답변 <br />"
                        + inquireCommand.getInquireAnswer().replace("\n", "<br />");
                String from = "hiland0@gamil.com";
                String to = memDto.getMemberEmail();
                emailSendService.mailsend(content, subject, from, to);
            }
        }catch(Exception e) {	} //메일링이 안되면 오류 발생할 수 있습니다.
    }
}