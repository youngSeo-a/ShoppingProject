package project.web.code.service.delivery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.web.code.mapper.delivery.DeliveryMapper;
import project.web.code.repository.DeliveryRepository;

@Service
@RequiredArgsConstructor
public class DeliveryUpdateService {
    private final DeliveryMapper deliveryMapper;
    public void execute(String purchaseNum) {
        deliveryMapper.deliveryStateUpdate(purchaseNum);
    }
}
