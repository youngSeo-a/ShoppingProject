package project.web.code.mapper.login;
import org.apache.ibatis.annotations.Mapper;
import project.web.code.dto.AuthInfoDTO;

@Mapper
public interface  LoginMapper {
    public String selectIdCheck(String userId);
    public String selectEmailCheck(String userEmail);
}
