package project.web.code.service.goodsIpgo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.dto.AuthInfoDTO;
import project.web.code.dto.GoodsIpgoCommand;
import project.web.code.dto.GoodsIpgoDTO;
import project.web.code.mapper.employee.EmployeeMapper;
import project.web.code.mapper.goodsIpgo.GoodsIpgoMapper;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class GoodsIpgoService {

    private final EmployeeMapper employeeMapper;
    private final GoodsIpgoMapper goodsIpgoMapper;

    public void execute(GoodsIpgoCommand goodsIpgoCommand, HttpSession session) {
        //입고한 직원이 누구인지 알기 위해 직원 로그인 정보를 가지고 온다.
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        //아이디가 아닌 직원번호가 필요하므로 아이디를 이용해서 직원정보를 가지고 온다.
        String empNum = employeeMapper.getEmpNum(auth.getUserId());
        // 입고 DTO를 만들어준다.
        GoodsIpgoDTO dto = new GoodsIpgoDTO();
        dto.setEmpNum(empNum);
        dto.setGoodsNum(goodsIpgoCommand.getGoodsNum());
        dto.setIpgoDate(goodsIpgoCommand.getIpgoDate());
        dto.setIpgoNum(goodsIpgoCommand.getGoodsIpgoNum());
        dto.setIpgoPrice(goodsIpgoCommand.getIpgoPrice());
        dto.setIpgoQty(goodsIpgoCommand.getIpgoQty());
        dto.setMadeDate(Timestamp.valueOf(goodsIpgoCommand.getMadeDate()));
        goodsIpgoMapper.goodsIpgoInsert(dto);
    }
}