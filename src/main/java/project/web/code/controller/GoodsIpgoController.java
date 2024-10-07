package project.web.code.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.web.code.dto.GoodsIpgoCommand;
import project.web.code.dto.GoodsIpgoDTO;
import project.web.code.service.goodsIpgo.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodsIpgoController {

    private final GoodsItemService goodsItemService;
    private final GoodsIpgoAutoNumservice goodsIpgoAutoNumservice;
    private final GoodsIpgoService goodsIpgoService;
    private final GoodsIpgoListService goodsIpgoListService;
    private final GoodsIpgoDetailService goodsIpgoDetailService;
    private final GoodsIpgoUpdateService goodsIpgoUpdateService;
    private final GoodsIpgoDeleteService goodsIpgoDeleteService;

    @GetMapping("/goodsIpgoDelete")
    public String goodsIpgoDelete(
            @RequestParam("ipgoNum") String ipgoNum,
            @RequestParam("num") String goodsNum) {
        goodsIpgoDeleteService.execute(ipgoNum, goodsNum);
        return "redirect:/goods/goodsIpgoList";
    }
    @PostMapping("/goodsIpgoModify")
    public String goodsIpgoModify(GoodsIpgoCommand goodsIpgoCommand) {
        goodsIpgoUpdateService.execute(goodsIpgoCommand);
        return "redirect:/goods/goodsIpgoList";
    }

    @GetMapping("/goodsIpgoUpdate")
    public String goodsIpgoUpdate(
            @RequestParam("ipgoNum") String ipgoNum,
            @RequestParam("num") String goodsNum,
            Model model) {// 상세보기나 수정페이지에서 보는 내용이 같다.
        GoodsIpgoDTO dto= goodsIpgoDetailService.execute(ipgoNum,goodsNum);
        model.addAttribute("dto", dto); // dto를 전달하는 것이 아니므로 model에 저장
        return "views/goodsIpgo/goodsIpgoUpdate";
    }

    @PostMapping("/goodsIpgoDetail")
    // @ResponseBody를 밖에 적어도 무관하다.
    public @ResponseBody GoodsIpgoDTO detail(
            @RequestParam("ipgoNum") String ipgoNum,
            @RequestParam("goodsNum") String goodsNum) {
        GoodsIpgoDTO dto= goodsIpgoDetailService.execute(ipgoNum,goodsNum);
        System.out.println("dto"+dto);
        return dto;
    }

    @PostMapping("/goodsIpgoList")
    public ModelAndView goodsIpgoList(Model model) {
        ModelAndView view = new ModelAndView();
        view.setViewName("jsonView");
        goodsIpgoListService.execute(model);
        return view;
    }
    @GetMapping("/goodsIpgoList")
    public String goodsIpgoList() {
        return "views/goodsIpgo/goodsIpgoList";
    }
    @PostMapping("/ipgoRegist" )
    public String ipgoRegist(GoodsIpgoCommand goodsIpgoCommand, HttpSession session) {
        goodsIpgoService.execute(goodsIpgoCommand, session);
        return "redirect:/goods/goodsIpgoList";
    }
    @GetMapping("/goodsIpgo" )
    public String goodsIpgo(Model model) {
        goodsIpgoAutoNumservice.execute(model);
        return "views/goodsIpgo/goodsIpgo";
    }
    @GetMapping("/goodsItem")
    public String goodsItem() {
        return "views/goodsIpgo/goodsItem";
    }

    @PostMapping("/goodsItemList")
    @ResponseBody // Map을 이용해 데이터를 html에 보내려면 RestAPI를 사용하지만 @ResponseBody를 이용.
    public Map<String, Object> goodsItem( //searchWord를 가져오기 위해 html에 검색 부분 추가
                                          @RequestParam(value = "searchWord", required = false) String searchWord,
                                          @RequestParam(value = "page" , required = false, defaultValue = "1" ) int page
    ){
        Map<String, Object> map = goodsItemService.execute(searchWord, page);
        return map;
    }
}
