package project.web.code.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import project.web.code.dto.GoodsDTO;
import project.web.code.dto.LoginCommand;
import project.web.code.dto.MemberDTO;
import project.web.code.service.CookiesService;
import project.web.code.service.MainGoodsListService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MainGoodsListService mainGoodsListService;
    private final CookiesService cookiesService;
    @RequestMapping("/emp")
    // 첫 페이지에 th:object="${loginCommand}"로 인해 발생하는 오류가 발생하지 하기 위해
    // command객체 생성
    public String index( /*LoginCommand loginCommand*/
            @ModelAttribute("loginCommand") LoginCommand loginCommand, Model model
            , HttpServletRequest request) {
        //index.html페이지가 열릴 때 상품정보를 가지고 와야 합니다.
        //index.html페이지가 열릴 때 쿠키가 있는지 확인해야겠죠..
        cookiesService.execute(request, model);
        return "views/index2";
    }
    @GetMapping("/shopping")
    public ModelAndView list(@RequestParam(name="nowPage", defaultValue = "0") int nowPage,
                             @RequestParam(value = "searchWord", required = false) String searchWord) {
        //보여줄 화면과 전송할 데이터를 담는 객체
        ModelAndView view = new ModelAndView();
        try {

            //파라메터 객체생성
            Map<String, Object> param = new HashMap<>();
            param.put("nowPage", nowPage);
            param.put("searchWord", searchWord);
            //서비스로부터 결과 객체를 받는다
            GoodsDTO.DateResponse response = mainGoodsListService.goodsSelectAll(param);
            //전송할 데이터를 key , value 로 담는다.
            view.addObject("data", response);

        }catch (Exception e) {
            e.printStackTrace();
        }

        view.setViewName("views/index"); //이동할 화면경로

        return view;
    }

}