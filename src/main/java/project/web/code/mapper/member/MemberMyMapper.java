package project.web.code.mapper.member;

import project.web.code.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMyMapper {
    public MemberDTO.Response memberInfo(String memberId);
    public int memberPwUpdate(String userPw, String memberId);
    public int memberDrop(String memberId);
    public int memberInfoUpdate(MemberDTO.Response dto);
}
