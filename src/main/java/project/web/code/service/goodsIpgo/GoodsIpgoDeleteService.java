package project.web.code.service.goodsIpgo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.mapper.goodsIpgo.GoodsIpgoMapper;

@Service
@RequiredArgsConstructor
public class GoodsIpgoDeleteService {
    private final GoodsIpgoMapper goodsIpgoMapper;

    public void execute(String ipgoNum, String goodsNum) {
        goodsIpgoMapper.ipgoGoodsNumDelete(ipgoNum, goodsNum);
    }
}