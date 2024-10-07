package project.web.code.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.web.code.dto.PurchaseCommand;
import project.web.code.mapper.purchase.PurchaseMapper;
import project.web.code.service.purchase.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final GoodsBuyService goodsBuyService;
    private final GoodsOrderService goodsOrderService;
    private final IniPayReqService iniPayReqService;
    private final IniPayReturnService iniPayReturnService;
    private final OrderProcessListService orderProcessListService;
    private final PaymentDeleteService paymentDeleteService;

    //이번에는 service를 만들지 않고 바로 interfase를 가져오겠습니다.
    private final PurchaseMapper purchaseMapper ;

    @GetMapping("/purchaseOk")
    public String purchaseOk(
            @RequestParam(value="purchaseNum")String purchaseNum) {
        purchaseMapper.purchaseOk(purchaseNum);
        return "redirect:/purchase/orderList";
    }

    @GetMapping("/paymentDel")
    public String paymentDel(
            @RequestParam("purchaseNum") String purchaseNum) {
        paymentDeleteService.execute(purchaseNum);
        return "redirect:/purchase/orderList";
    }

    @GetMapping("/orderList")
    public String orderList(HttpSession session, Model model) {
        orderProcessListService.execute(session, model);
        return "views/purchase/orderList";
    }

    @PostMapping("/INIstdpay_pc_return")
    public String payReturn(HttpServletRequest request, HttpSession session, Model model) {
        iniPayReturnService.execute(request, session, model);
        return "views/purchase/buyfinished";
    }
    @GetMapping("/close")
    public String close() {
        return "views/purchase/close";
    }

    @GetMapping("/paymentOk")
    public String paymentOk(
            @RequestParam(value="purchaseNum") String purchaseNum
            ,HttpSession session
            ,Model model) {
        try {
            iniPayReqService.execute(purchaseNum,model,session);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// 이제 결제 페이지로 이동하겠습니다.
        return "views/purchase/payment";
    }

    @PostMapping("/goodsOrder")
    public String goodsOrder(PurchaseCommand purchaseCommand, HttpSession session, Model model,
                             HttpServletResponse response) {
        String purchaseNum = goodsOrderService.execute(purchaseCommand,session, model);
        // paymentOk를 만들어 주겠습니다.
        return "redirect:/purchase/paymentOk?purchaseNum="+purchaseNum;
    }


    @RequestMapping("/goodsBuy")
    public String goodsBuy(
            @RequestParam(value="prodCk") String [] prodCk,// check박스가 배열로 전송된다.
            HttpSession session, Model model) {
        goodsBuyService.execute(prodCk,session,model);
        return "views/purchase/goodsOrder";
    }
}