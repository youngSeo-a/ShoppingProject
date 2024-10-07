package project.web.code.mapper.cart;

import project.web.code.dto.CartDTO;
import project.web.code.dto.CartGoodsDTO;
import project.web.code.dto.GoodsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import project.web.code.dto.MemberDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartMapper {
        public List<GoodsDTO.Response> goodsSelectAll(Map<String, Object> param) throws Exception;
    public int goodsCount(String searchWord);
    public int cartInsert(CartDTO dto);
    public List<CartGoodsDTO> cartList(@Param("memberNum") String memberNum,
                                       @Param("goodsNums") String [] prodCk);
    public Integer sumPrice(String memberNum);
    public int goodsNumsDelete(Map<String, Object> condition);
    public int goodsNumDelete(@Param("goodsNum") String goodsNum,@Param("memberNum") String memberNum);
    public int cartQtyDown(@Param("goodsNum") String goodsNum,@Param("memberNum") String memberNum);

    // 조인문 없이 조인을 하기 위해서는 두개의 테이블 검색 쿼리가 각각 존재해야 한다.
    public GoodsDTO.Response goodsSelect(String goodsNum); // 그래서 각각 상품과 장바구니 쿼리문을 만들어주겠음.
    public CartDTO cartSelect(Integer cartNum); // 앞서 cartNum이 필요한데 테이블에는 cartNum이 없어 추가해보겠음.
    // 두 테이블로 부터 데이터를 가지고 오기 위한 메서드를 만들어줍니다.
    // 두테이블의 데이터를 가지고 오는 것이므로 CartGoodsDTO로 받아오겠음.
    public  CartGoodsDTO goodsPriceMulCartQty(@Param("goodsNum")String goodsNum,
                                              @Param("memberNum") String memberNum);
    public int cartGoodsDeletes(CartDTO dto);
}
