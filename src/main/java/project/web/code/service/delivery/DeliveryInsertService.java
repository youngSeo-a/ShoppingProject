package project.web.code.service.delivery;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import project.web.code.dto.DeliveryCommand;
import project.web.code.dto.DeliveryDTO;
import project.web.code.mapper.delivery.DeliveryMapper;
import project.web.code.repository.DeliveryRepository;

@Service
@RequiredArgsConstructor
public class DeliveryInsertService {
    // 이번에 interface를 사용하지 않고  class를 사용하겠습니다.
    private final DeliveryMapper deliveryMapper;

    public void execute(DeliveryCommand deliveryCommand) {
        DeliveryDTO deliveryDTO= new DeliveryDTO();
        // command에 있는 값을 dto에 복사해준다./
        BeanUtils.copyProperties(deliveryCommand, deliveryDTO);
        deliveryMapper.deliveryInsert(deliveryDTO);
    }
}
