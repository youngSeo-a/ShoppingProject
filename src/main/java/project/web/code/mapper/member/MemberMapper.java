package project.web.code.mapper.member;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import project.web.code.dto.MemberDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {
    public void memberInsert(MemberDTO.Response dto);
    public String memberAutoNum();
    List<MemberDTO.Response> selectAll(Map<String, Object> param) throws Exception;
    public int memberCount(String searchWord);
    public  int membersDelete(@Param("membersNum")String [] memDels);
    public MemberDTO.Response memberSelectOne(String memberNum);
    public int memberUpdate(MemberDTO.Response memDTO);
    public int memberDelete(String memberNum);
}
