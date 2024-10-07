package project.web.code.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.web.code.dto.DeliveryCommand;
import project.web.code.service.delivery.DeliveryDeleteService;
import project.web.code.service.delivery.DeliveryInsertService;
import project.web.code.service.delivery.DeliveryUpdateService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryInsertService deliveryInsertService;

    private final DeliveryDeleteService deliveryDeleteService;

    private final DeliveryUpdateService deliveryUpdateService;
    @GetMapping("/deliveryState")
    public String deliveryState(@RequestParam("purchaseNum") String purchaseNum) {
        deliveryUpdateService.execute(purchaseNum);
        return "redirect:/purchase/purchaseList";
    }

    @PostMapping("/deliveryAction")
    public String deliveryAction(DeliveryCommand deliveryCommand) {
        deliveryInsertService.execute(deliveryCommand);
        return "redirect:/purchase/purchaseDetail?purchaseNum="+deliveryCommand.getPurchaseNum();
    }
    @PostMapping("/deliveryDel")
    public String deliveryDel(
            @RequestParam("purchaseNum") String purchaseNum){
        deliveryDeleteService.execute(purchaseNum);
        return "redirect:/purchase/purchaseDetail?purchaseNum="+purchaseNum;
    }

}
