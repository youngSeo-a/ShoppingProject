package project.web.code.service;

import lombok.RequiredArgsConstructor;
import project.web.code.mapper.login.LoginMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailCheckService {
    private final LoginMapper loginMapper;
    public String execute(String userEmail){
        String resultEmail = loginMapper.selectEmailCheck(userEmail);
      return resultEmail;

    }
}
