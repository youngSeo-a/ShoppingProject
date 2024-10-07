package project.web.code.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.web.code.dto.GoodsCommand;
import project.web.code.dto.GoodsDTO;
import project.web.code.service.goods.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodsController {
    private final GoodsAutoNumService goodsAutoNumService;
    private final GoodsListService goodsListService;
    private final GoodsWriteService goodsWriteService;
    private final ProductsDeleteService productsDeleteService;
    private final GoodsDetailService goodsDetailService;
    private final GoodsUpdateService goodsUpdateService;
    private final GoodsDeleteService goodsDeleteService;
    @GetMapping("/goodsList")
    public ModelAndView goodsList(@RequestParam(name="nowPage", defaultValue = "0") int nowPage,
                             @RequestParam(value = "searchWord", required = false) String searchWord) {
        //보여줄 화면과 전송할 데이터를 담는 객체
        ModelAndView view = new ModelAndView();
        try {

            //파라메터 객체생성
            Map<String, Object> param = new HashMap<>();
            param.put("nowPage", nowPage);
            param.put("searchWord", searchWord);
            //서비스로부터 결과 객체를 받는다
            GoodsDTO.DateResponse response = goodsListService.selectAll(param);
            //전송할 데이터를 key , value 로 담는다.
            view.addObject("data", response);

        }catch (Exception e) {
            e.printStackTrace();
        }
        view.setViewName("views/goods/goodsList"); //이동할 화면경로

        return view;
    }

    @GetMapping("/goodsForm")
    public String goodsForm(){
        return "views/goods/goodsWrite";
    }

    @GetMapping("/goodsWrite")
    public String goodsWrite(Model model) {
        goodsAutoNumService.execute(model);
        return "views/goods/goodsForm";
    }

    @PostMapping("/goodsWrite")
    public String goodsWrite(@Validated GoodsCommand goodsCommand, BindingResult result
            , HttpSession session) {
        if(result.hasErrors()) {
            return "views/goods/goodsForm";
        }
        //메인이미지는 필수 이므로 파일선택을 안한 경우 경고 메시지를 보냅니다.
        // 오류메시지를 command에게 설정하지 못합니다. 메시지는 String인데 파일은 파일객체라 문자열을 인식하지 못합니다.
        if(goodsCommand.getGoodsMainStore().getOriginalFilename().isEmpty()) {
            result.rejectValue("goodsMainStore", "goodsCommand.goodsMainStore", "대문이미지를 선택해주세요.");
            return "views/goods/goodsForm"; // 오류메시지를 보내기 위해 현페이지를 열어 줍니다.
        }
        goodsWriteService.execute(goodsCommand, session);
        //오류가 없는 경우 goodsForm.html을 넘겨주고 정상이면 goodsRedirect.html을 넘겨준다.
        return "redirect:/goods/goodsList";
    }

    @PostMapping("/productsDelete")
    public String productsDelete(//체크박스에 의해 전달 된 값을 배열로 받습니다.
                                 @RequestParam(value = "memDels") String memDels[]) {
        productsDeleteService.execute(memDels);
        return "redirect:/goods/goodsList";
    }

    @GetMapping("/goodsDetail")
    public String goodsDetail(@RequestParam("goodsNum") String goodsNum
            ,Model model) {
        goodsDetailService.execute(goodsNum, model);
        return "views/goods/goodsInfo";
    }

    @GetMapping("/goodsUpdate")
    public String goodsUpdate(@RequestParam("goodsNum") String goodsNum, Model model
            , HttpSession session) {
        // 삭제할 파일을 선택한 후 다시 수정페이지로 오면 삭제할 파일정보를 가진 session이 존재하여 오류의 소지가 있다.
        // 그래서 수정 페이지에 오면 삭제할 파일정보를 가진 session을 제거 하는 것이 좋다.
        session.removeAttribute("fileList"); // 삭제할 파일 정보를 가지고 있는 session삭제
        goodsDetailService.execute(goodsNum, model);//수정을 하려면 기본 정보를 가져와야 하므로 goodsDetailService를 사용
        return "views/goods/goodsModify";
    }


    @PostMapping("/goodsUpdate")
    public String goodsUpdate(@Validated GoodsCommand goodsCommand,BindingResult result,
                              HttpSession session, Model model) {
        goodsUpdateService.execute(goodsCommand, session, result, model);
        if(result.hasErrors()) {
            return "views/goods/goodsModify";
        }
        return "redirect:/goods/goodsDetail?goodsNum="+goodsCommand.getGoodsNum();
    }


    @RequestMapping("/goodsDel/{goodsNum}")
    public String goodsDel(@PathVariable("goodsNum") String goodsNum) {
      //  goodsDeleteService.execute(goodsNum);
        return "redirect:../goodsList"; //PathVariable인 경우에는 주소 앞에 .. 을 꼭해줘야 합니다.
    }
}
