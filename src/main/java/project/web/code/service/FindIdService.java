package project.web.code.service;

import lombok.RequiredArgsConstructor;
import project.web.code.mapper.find.FindMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class FindIdService {
    private final FindMapper findMapper;

    public void execute(String userPhone, String userEmail, Model model) {
        String userId = findMapper.findId(userPhone, userEmail);
        model.addAttribute("userId", userId);
    }
}