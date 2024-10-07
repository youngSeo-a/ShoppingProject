package project.web.code.service.employees;

import lombok.RequiredArgsConstructor;
import project.web.code.dto.EmployeeCommand;
import project.web.code.mapper.employee.EmployeeMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class EmployeeAutoNumService {

   private final EmployeeMapper employeeMapper;
    public void execute(Model model){
        String empNum = employeeMapper.autoNum();
        EmployeeCommand employeeCommand = new EmployeeCommand();
        employeeCommand.setEmpNum(empNum);
        model.addAttribute("employeeCommand", employeeCommand);
    }
}
