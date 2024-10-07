package project.web.code.mapper.goods;

import project.web.code.dto.GoodsDTO;
import project.web.code.dto.GoodsDetailStockDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import project.web.code.dto.MemberDTO;
import project.web.code.dto.StartEndPageDTO;
import project.web.code.service.goodsIpgo.GoodsIpgoAutoNumservice;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsMapper {
    //원래 마이바티스는 인자를 하나만 사용해야 하는 데 간 혹 인자를 여러개를 사용해야 하는 경우가 있을 때 @Param을 사용
    public String ipgoAndGoodsAutoNum(@Param("sep") String seploginCon
            ,@Param("columnName") String columnName
            ,@Param("seq") Integer seq
            ,@Param("tableName") String tableName);
    public int goodsInsert(GoodsDTO.Response dto);
    public List<GoodsDTO.Response> selectAll(Map<String, Object> param) throws Exception;
    public List<GoodsDTO> allSelect(StartEndPageDTO sepDTO);
    public int goodsCount(String searchWord);
    //배열로 전달 된값을 마이바티스에서 사용하려면 @Param을 사용합니다
    public int productsDelete(@Param("products") String products[]);
    public GoodsDTO.Response selectOne(String goodsNum);
    public int goodsUpdate(GoodsDTO.Response dto);
    public int goodsDelete(String goodsNum);
    public GoodsDetailStockDTO goodsDetailStock(String goodsNum);
    public int visitCount(String goodsNum);
    public List<GoodsDTO.Response> wishGoodsList(String memberNum);
}