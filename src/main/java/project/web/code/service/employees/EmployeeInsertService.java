package project.web.code.service.employees;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.web.code.dto.EmployeeCommand;
import project.web.code.dto.EmployeeDTO;
import project.web.code.dto.MemberDTO;
import project.web.code.mapper.employee.EmployeeMapper;

@Service
@RequiredArgsConstructor
public class EmployeeInsertService {

    private final PasswordEncoder passwordEncoder;
    private final EmployeeMapper employeeMapper;

    public void execute(EmployeeCommand employeeCommand) {
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
                //비밀번호를 암호화.
                .empPw(passwordEncoder.encode(employeeCommand.getEmpPw()))
                .build();
        employeeMapper.employeeInsert(dto);
    }
}
