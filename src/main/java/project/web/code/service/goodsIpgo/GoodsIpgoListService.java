package project.web.code.service.goodsIpgo;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.web.code.dto.GoodsIpgoDTO;
import project.web.code.mapper.goodsIpgo.GoodsIpgoMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsIpgoListService {

   private final GoodsIpgoMapper goodsIpgoMapper;
    public void execute(Model model) {
        List<GoodsIpgoDTO> list = goodsIpgoMapper.goodsIpgoAllSelect();
        model.addAttribute("list", list); //ModeAndView이므로 model에 담아 볼낼 수 있습니다.
    }
}