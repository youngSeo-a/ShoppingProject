package project.web.code.mapper.employee;

import project.web.code.dto.EmployeeDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMyMapper {
    public EmployeeDTO.Response employeeInfo(String empId);
    public int employeeInfoUpdate(EmployeeDTO.Response dto);
    public int employeePwUpdate(String userPw, String empId);
}