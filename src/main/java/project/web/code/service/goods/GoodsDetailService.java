package project.web.code.service.goods;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.web.code.dto.GoodsDTO;
import project.web.code.mapper.goods.GoodsMapper;

@Service
@RequiredArgsConstructor
public class GoodsDetailService {
    private final GoodsMapper goodsMapper;

    public void execute(String goodsNum, Model model) {
        GoodsDTO.Response dto = goodsMapper.selectOne(goodsNum);
        model.addAttribute("goodsCommand", dto);
        // \n을 <br />로 변경하기 위해서 필요합니다.
        model.addAttribute("newLine", "\n");
    }
}