package project.web.code.mapper.goodsIpgo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import project.web.code.dto.GoodsIpgoDTO;


import java.util.List;

@Mapper
public interface GoodsIpgoMapper {
    public List<GoodsIpgoDTO> goodsIpgoAllSelect();
    public int goodsIpgoInsert(GoodsIpgoDTO dto);
    // arg0        // arg1
    public GoodsIpgoDTO selectIpgoGoods(String ipgoNum, String goodsNum);
    public int goodsIpgoUpdate(GoodsIpgoDTO dto);
    public int ipgoGoodsNumDelete(@Param("ipgoNum") String ipgoNum,
                                  @Param("goodsNum") String goodsNum);
}