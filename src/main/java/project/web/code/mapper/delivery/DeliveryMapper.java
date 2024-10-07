package project.web.code.mapper.delivery;

import org.apache.ibatis.annotations.Mapper;
import project.web.code.dto.DeliveryDTO;

@Mapper
public interface DeliveryMapper {
    public Integer deliveryInsert(DeliveryDTO deliveryDTO);
    public Integer deliveryDelete(String purchaseNum);
    public Integer deliveryStateUpdate(String purchaseNum) ;

}
