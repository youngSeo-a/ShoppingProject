package project.web.code.service.goods;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.mapper.goods.GoodsMapper;

@Service
@RequiredArgsConstructor
public class ProductsDeleteService {

    private final GoodsMapper goodsMapper;
     
    public void execute(String products[]) {
        goodsMapper.productsDelete(products);
    }
}