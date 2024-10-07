package project.web.code.service.employees;

import lombok.RequiredArgsConstructor;
import project.web.code.dto.AuthInfoDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import project.web.code.dto.EmployeeCommand;
import project.web.code.dto.EmployeeDTO;
import project.web.code.mapper.employee.EmployeeMyMapper;

import javax.servlet.http.HttpSession;


@Service
@RequiredArgsConstructor
public class EmployeeInfoUpdateService {
    // 비밀번호를 비교하게 PasswordEncoder를 가져옴니다.
    private final  PasswordEncoder passwordEncoder;
    // 디비에 저장
    private final EmployeeMyMapper employeeMyMapper;

    public int execute(EmployeeCommand employeeCommand , HttpSession session, BindingResult result) {
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        if(! passwordEncoder.matches(employeeCommand.getEmpPw(), auth.getUserPw())) {
            result.rejectValue("empPw", "employeeCommand.empPw", "비밀번호가 틀렸습니다.");
            return 0;
        }else {
            //command로 받아온 값을 dto에 저장
 //           EmployeeDTO dto = new EmployeeDTO();
//            dto.setEmpAddr(employeeCommand.getEmpAddr());
//            dto.setEmpAddrDetail(employeeCommand.getEmpAddrDetail());
//            dto.setEmpEmail(employeeCommand.getEmpEmail());
//            dto.setEmpId(employeeCommand.getEmpId());
//            dto.setEmpJumin(employeeCommand.getEmpJumin());
//            dto.setEmpName(employeeCommand.getEmpName());
//            dto.setEmpNum(employeeCommand.getEmpNum());
//            dto.setEmpPhone(employeeCommand.getEmpPhone());
//            dto.setEmpPost(employeeCommand.getEmpPost());
//            dto.setEmpPw(employeeCommand.getEmpPw());
//            dto.setEmpRegiDate(employeeCommand.getEmpRegiDate());

            EmployeeDTO.Response dto= null;
            dto= EmployeeDTO.Response
                    .builder()
                    .empNum(employeeCommand.getEmpNum())
                    .empAddr(employeeCommand.getEmpAddr())
                    .empAddrDetail(employeeCommand.getEmpAddrDetail())
                    .empJumin(employeeCommand.getEmpJumin())
                    .empEmail(employeeCommand.getEmpEmail())
                    .empId(employeeCommand.getEmpId())
                    .empName(employeeCommand.getEmpName())
                    .empPhone(employeeCommand.getEmpPhone())
                    .empPost(employeeCommand.getEmpPost())
                    .empRegiDate(employeeCommand.getEmpRegiDate())
                    //비밀번호를 암호화.
                    .empPw(passwordEncoder.encode(employeeCommand.getEmpPw()))
                    .build();
            employeeMyMapper.employeeInfoUpdate(dto);
            return 1;
        }
    }
}