package project.web.code.service.employees;

import lombok.RequiredArgsConstructor;
import project.web.code.mapper.employee.EmployeeMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeDeleteService {

    private final EmployeeMapper employeeMapper;
    public void execute(String empNum) {
        employeeMapper.employeeDelete(empNum);
    }
}
