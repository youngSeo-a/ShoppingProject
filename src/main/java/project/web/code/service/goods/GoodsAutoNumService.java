package project.web.code.service.goods;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.web.code.dto.GoodsCommand;
import project.web.code.mapper.goods.GoodsMapper;

@Service
@RequiredArgsConstructor
public class GoodsAutoNumService {

   private final GoodsMapper goodsMapper;
    public void execute(Model model) {
        String goodsNum = goodsMapper.ipgoAndGoodsAutoNum("gd","goods_num",3,"goods_info");
        //나중에 command를 사용해야 하므로 command를 먼저 만들고 command에 상품번호를 저장합니다.
        GoodsCommand goodsCommand = new GoodsCommand();
        goodsCommand.setGoodsNum(goodsNum);
        model.addAttribute("goodsCommand", goodsCommand);
    }
}