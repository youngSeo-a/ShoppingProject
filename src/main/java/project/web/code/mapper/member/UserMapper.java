package project.web.code.mapper.member;

import project.web.code.dto.AuthInfoDTO;
import project.web.code.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface UserMapper {
    public int userInsert(MemberDTO.Response dto);
    public int userCheckUpdate(String email);
    public AuthInfoDTO loginSelect(String userId);
}
