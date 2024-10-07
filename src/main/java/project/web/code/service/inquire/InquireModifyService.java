package project.web.code.service.inquire;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.dto.InquireCommand;
import project.web.code.dto.InquireDTO;
import project.web.code.mapper.inquire.InquireMapper;
import project.web.code.repository.InquireRepository;

@Service
@RequiredArgsConstructor
public class InquireModifyService {

    private final InquireMapper inquireMapper;
    public void execute(InquireCommand inquireCommand) {
        InquireDTO dto = new InquireDTO();
        dto.setInquireContent(inquireCommand.getInquireContent());
        dto.setInquireKind(inquireCommand.getInquireKind());
        dto.setInquireNum(inquireCommand.getInquireNum());
        dto.setInquireSubject(inquireCommand.getInquireSubject());
        inquireMapper.inquireUpdate(dto);
    }
}
