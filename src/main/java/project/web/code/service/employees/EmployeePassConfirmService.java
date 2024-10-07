package project.web.code.service.employees;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.web.code.dto.AuthInfoDTO;
import project.web.code.mapper.employee.EmployeeMyMapper;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class EmployeePassConfirmService {

    private final PasswordEncoder passwordEncoder;
    private final EmployeeMyMapper employeeMyMapper;

    public boolean execute(String newPw, String oldPw, HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        if(passwordEncoder.matches(oldPw, auth.getUserPw())){
            String userPw = passwordEncoder.encode(newPw);
            //변경된 비밀번호를 디비에 저장한다.
            employeeMyMapper.employeePwUpdate(userPw, auth.getUserId());
            //변경된 비밀번호를 seesion에 저장한다.
            auth.setUserPw(userPw);
            return true;
        }else {
            return false;
        }
    }
}