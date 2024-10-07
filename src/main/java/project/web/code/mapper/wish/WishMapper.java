package project.web.code.mapper.wish;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WishMapper {
    public Integer wishGoodsSelect(@Param("goodsNum")String goodsNum,
                                   @Param("memberNum")String getMemberNum);

    public int wishInsert(@Param("goodsNum")String goodsNum,
                          @Param("memberNum")String memberNum);

    public int wishDelete(@Param("goodsNum") String goodsNum,
                         @Param("memberNum")String memberNum);

    public int wishGoodsDeletes( @Param("goodsNums") String [] wishGoodsDels,
                                 @Param("memberNum") String memberNum);
}
