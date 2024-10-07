package project.web.code.service.employees;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.mapper.employee.EmployeeMapper;

@Service
@RequiredArgsConstructor
public class EmployeesDeleteService {

    private final EmployeeMapper employeeMapper;
    public void execute(String empsDel []) {
        employeeMapper.employeesDelete(empsDel);
    }
}