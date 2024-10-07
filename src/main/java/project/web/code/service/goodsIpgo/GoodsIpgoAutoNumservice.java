package project.web.code.service.goodsIpgo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.web.code.mapper.goods.GoodsMapper;

@Service
@RequiredArgsConstructor
public class GoodsIpgoAutoNumservice {

    private final GoodsMapper goodsMapper; //입고번호 받아오는 것을 goodsAutoNum을 사용해서 받아 오겠습니다.
    public void execute(Model model) {
        String num = goodsMapper.ipgoAndGoodsAutoNum("hk","goods_num",3,"goods_info");
        model.addAttribute("ipgoNum", num);
    }
}