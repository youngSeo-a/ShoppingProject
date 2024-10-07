package project.web.code.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.web.code.dto.CartGoodsDTO;
import project.web.code.service.conner.*;
import project.web.code.service.goods.GoodsDetailService;
import project.web.code.service.goods.GoodsDetailViewService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/corner")
@RequiredArgsConstructor
public class CornerController {

    public final GoodsDetailViewService goodsDetailViewService;
    public final GoodsWishService goodsWishService;
    public final GoodsWishListService goodsWishListService;
    public final WishGoodsDelsService wishGoodsDelsService;
    public final WishDelService wishDelService;
    public final CartInsertService cartInsertService;
    public final CartListService cartListService;
    public final GoodsCartDelsService goodsCartDelsService;
    public final GoodsCartDelService goodsCartDelService;
    public final CartQtyDownService cartQtyDownService;
    public final GoodsDetailService goodsDetailService;

    @RequestMapping("/goodsDescript")
    public String goodsDescript(
            @RequestParam(value="goodsNum") String goodsNum,
            Model model) {
        goodsDetailService.execute(goodsNum, model);
        return "views/corner/goodsDescript";
    }

        @GetMapping("/buyItem")
    public String buyItem( // 바로구매할 상품을 장바구니에 넣고 결제정보 페이지로 이동하면 바로구매.
                           @RequestParam(value="goodsNum") String goodsNum,
                           @RequestParam(value="qty") Integer qty,
                           HttpSession session, HttpServletResponse response) {
        // 먼저 장바구니에 넣는다.
        String result = cartInsertService.execute(goodsNum, qty, session);
        if(result == "999") {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out;
            try {
                out = response.getWriter();
                String str = "<script>"
                        + "alert('관리자는 구매할 수 없습니다.');"
                        + "location.href='/corner/detailView/"+goodsNum+"';" //장바구니에 안들어 갔으면 상품페이지
                        + "</script>";
                out.print(str);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(result == "000") {
            return "redirect:/shopping"; //홈으로
        }
        //정산적으로 처리 되었다면 결제정보 입력페이지로 이동
        return "redirect:/purchase/goodsBuy?prodCk="+goodsNum;
    }

    @GetMapping("/cartDel")
    public String cartDel(
            @RequestParam("goodsNum") String goodsNum,
            HttpSession session) {
        goodsCartDelService.execute(goodsNum, session);
        return "redirect:/corner/cartList";
    }

    @PostMapping("/cartDels")
    @ResponseBody
    public String cartdel(// javascript 배열을 받을 이름에 배열 표시를 해줘야함.
                          @RequestParam("goodsNums[]") String goodsNums[], //배열이므로 배열로 받아오겠음.
                          HttpSession session) {
        return goodsCartDelsService.execute(goodsNums, session);
    }


    @GetMapping("cartList")
    public String cartList(Model model, HttpSession session) {
        cartListService.execute(model, session);
        return "views/corner/cartList";
    }

    // 비동기식이므로 ajax에 값을 전달하기 위해서는 ResTAPI 또는 @ResponseBody를 사용해야한다.
    @GetMapping("/cartAdd")
    @ResponseBody
    public String cartAdd(
            @RequestParam(value="goodsNum") String goodsNum,
            @RequestParam(value="qty") Integer qty,
            HttpSession session) {

        return cartInsertService.execute(goodsNum, qty, session);
    }


    @GetMapping("/cartQtyDown")
    public void cartQtyDown(
            @RequestParam(value="goodsNum") String goodsNum,
            HttpSession session,HttpServletResponse response) {
        CartGoodsDTO dto = cartQtyDownService.execute(goodsNum, session);
        //  DTO를  model로 전달하지 않고 ObjectMapper를 사용.
        ObjectMapper mapper = new ObjectMapper();
        response.setCharacterEncoding("utf-8");
        // response를 통해 ObjectMapper를 ajax에 전달.
        try {
            response.getWriter().print(mapper.writeValueAsString(dto));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @GetMapping("/wishDel")
    public String wishDel(@RequestParam("goodsNum")String goodsNum,
                          HttpSession session) {
        wishDelService.execute(goodsNum, session);
        return "redirect:/corner/wishList";
    }

    @PostMapping("/goodsWishDels")
    public String goodsWishDels(
            @RequestParam("wishGoodsDel") String wishGoodsDels [],
            HttpSession session) {
        wishGoodsDelsService.execute(wishGoodsDels, session);
        return "redirect:/corner/wishList";
    }

    @GetMapping("/wishList")
    public String wishList(HttpSession session, Model model) {
        goodsWishListService.execute(session, model);
        return "views/corner/wishList";
    }

    @PostMapping("/goodsWishAdd")
    public @ResponseBody String goodsWishAdd(
                                              @RequestParam("goodsNum") String goodsNum,
                                              HttpSession session) { // 누구의 관심상품인지 알기 위해서는 로그인정보가 필요.
        return goodsWishService.execute(goodsNum, session);
    }

    //index.html에서 주소를 pathValue로 전달.
    @GetMapping("/detailView/{goodsNum}")
    public String prodInfo(@PathVariable("goodsNum") String goodsNum,
                           Model model, HttpSession session) {
        goodsDetailViewService.execute(goodsNum, model, session);
        return "views/corner/detailView";
    }
    
}
