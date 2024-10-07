package project.web.code.mapper.inquire;

import org.apache.ibatis.annotations.Mapper;
import project.web.code.dto.InquireDTO;

import java.util.List;

@Mapper
public interface InquireMapper {
    public Integer inquireInsert(InquireDTO dto);
    public List<InquireDTO> inquireList(String goodsNum , Integer inquireNum);
    public Integer inquireAnswerUpdate(InquireDTO dto) ;
    public Integer inquireUpdate(InquireDTO dto) ;
    public Integer inquireDelete(Integer inquireNum);

}
