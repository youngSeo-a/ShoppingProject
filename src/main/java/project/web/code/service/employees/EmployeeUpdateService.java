package project.web.code.service.employees;

import lombok.RequiredArgsConstructor;
import project.web.code.dto.EmployeeCommand;
import project.web.code.dto.EmployeeDTO;
import project.web.code.mapper.employee.EmployeeMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeUpdateService {

    private final EmployeeMapper employeeMapper;
    public void execute(EmployeeCommand employeeCommand) {
//        EmployeeDTO dto = new EmployeeDTO();
//        dto.setEmpAddr(employeeCommand.getEmpAddr());
//        dto.setEmpAddrDetail(employeeCommand.getEmpAddrDetail());
//        dto.setEmpJumin(employeeCommand.getEmpJumin());
//        dto.setEmpEmail(employeeCommand.getEmpEmail());
//        dto.setEmpName(employeeCommand.getEmpName());
//        dto.setEmpNum(employeeCommand.getEmpNum());
//        dto.setEmpPhone(employeeCommand.getEmpPhone());
//        dto.setEmpPost(employeeCommand.getEmpPost());
//        dto.setEmpRegiDate(employeeCommand.getEmpRegiDate());
//        // dto에 저장한 값을 디비에 저장합니다.
//        employeeMapper.employeeUpdate(dto);
    }
}