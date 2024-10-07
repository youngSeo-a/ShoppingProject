package project.web.code.service.goodsIpgo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.dto.GoodsIpgoDTO;
import project.web.code.mapper.goodsIpgo.GoodsIpgoMapper;

@Service
@RequiredArgsConstructor
public class GoodsIpgoDetailService {

   private final GoodsIpgoMapper goodsIpgoMapper;
    public GoodsIpgoDTO execute(String ipgoNum, String goodsNum) {
        GoodsIpgoDTO dto = goodsIpgoMapper.selectIpgoGoods(ipgoNum, goodsNum);
        return dto;
    }
}