package project.web.code.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.mapper.member.UserMapper;

@Service
@RequiredArgsConstructor
public class UserEmailCheckService {

   private final UserMapper userMapper;
    public int execute(String email){
        int i = userMapper.userCheckUpdate(email);
        return i;
    }
}
