package project.web.code.service;

import lombok.RequiredArgsConstructor;
import project.web.code.mapper.login.LoginMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdcheckService {
    private final LoginMapper loginMapper;
    public String execute(String userId) {
        String resultId= loginMapper.selectIdCheck(userId);
        return resultId;
    }
}

