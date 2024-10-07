package project.web.code.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.web.code.service.purchase.PurchaseDetailService;
import project.web.code.service.purchase.PurchaseListService;
import project.web.code.service.purchase.PurchaseStatusService;


@Controller
@RequiredArgsConstructor
@RequestMapping("/purchase")
public class EmpPurchaseController {

    public final PurchaseListService purchaseListService;
    public final PurchaseStatusService purchaseStatusService;
    public final PurchaseDetailService purchaseDetailService;

    @GetMapping("/purchaseDetail")
    public String purchaseDetail(
            @RequestParam(value="purchaseNum") String purchaseNum
            , Model model) {
        purchaseDetailService.execute(purchaseNum, model);
        model.addAttribute("newLineChar", "\n");
        return "views/purchase/purchaseDetail";
    }

    @GetMapping("/purchaseStatus")
    public String purchaseStatus(
            @RequestParam("purchaseNum")String purchaseNum) {
        purchaseStatusService.execute(purchaseNum);
        return "redirect:/purchase/purchaseList";
    }

    @GetMapping("/purchaseList")
    public String purchaseList(Model model) {
        purchaseListService.execute(model);
        return "views/purchase/purchaseList";
    }
}
