package project.web.code.mapper.employee;

import project.web.code.dto.EmployeeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmployeeMapper {
    public String autoNum();
    public Integer employeeInsert(EmployeeDTO.Response dto);
    public List<EmployeeDTO.Response> employeeAllSelect(Map<String, Object> param) throws Exception;
    public int employeeCount(String searchWord);
    public Integer employeesDelete( @Param("employeesNum") String empsDel[]);
    public EmployeeDTO.Response employeeOneSelect(String empNum);
    public Integer employeeUpdate(EmployeeDTO.Response dto);
    public Integer employeeDelete(String empNum);
    public String getEmpNum(String empId);
}
