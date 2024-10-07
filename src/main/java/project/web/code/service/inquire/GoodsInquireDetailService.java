package project.web.code.service.inquire;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.web.code.dto.InquireDTO;
import project.web.code.mapper.inquire.InquireMapper;
import project.web.code.repository.InquireRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsInquireDetailService {
    private final InquireMapper inquireMapper;

    public void execute(Integer inquireNum, Model model) {
        List<InquireDTO> list = inquireMapper.inquireList(null,inquireNum);
        model.addAttribute("list", list);
    }
}