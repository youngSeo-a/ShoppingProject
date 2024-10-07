package project.web.code.mapper.find;

import project.web.code.dto.AuthInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FindMapper {
    public String findId(@Param("userPhone")String userPhone, @Param("userEmail")String userEmail);

    public AuthInfoDTO userEmail(@Param("_userId") String userId,
                                 @Param("_userPhone") String userPhone);

    public int pwUpdate(AuthInfoDTO dto);


}
