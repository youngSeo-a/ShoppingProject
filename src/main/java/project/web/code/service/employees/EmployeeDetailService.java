package project.web.code.service.employees;

import lombok.RequiredArgsConstructor;
import project.web.code.dto.EmployeeDTO;
import project.web.code.mapper.employee.EmployeeMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class EmployeeDetailService {

    private final EmployeeMapper employeeMapper ;
    public void execute(String empNum, Model model) {
        EmployeeDTO.Response vo = employeeMapper.employeeOneSelect(empNum);
        model.addAttribute("employeeCommand", vo);
    }
}