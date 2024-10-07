package project.web.code.controller;

import lombok.RequiredArgsConstructor;
import project.web.code.dto.MemberCommand;
import project.web.code.service.memberMyPage.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/myPage")
@RequiredArgsConstructor
public class MemberMyPageController {

    private final MemberInfoService memberInfoService;
    private final MemberPwModifyService memberPwModifyService;
    private final MyPassConfirmService myPassConfirmService;
    private final MemberDropService memberDropService;
    private final MemberInfoUpdateService memberInfoUpdateService;

    @GetMapping("/myDetail")
    //로그인 할때 저장한 session을 가져와서 내정보를 디비에서 가져오도록 해봅니다.
    // 디비로 가져온 정보를 model을 이용해서 myInfo.html에 보내도록한다.
    public String myDetail(HttpSession session,Model model) {
        memberInfoService.execute(session, model);
        return "views/memberShip/myInfo";
    }
    @GetMapping("/userPwModify")
    public String userPwModify() {
        return "views/memberShip/myPwCon";
    }


    @PostMapping("/memberPwModify")
    public String userPwModify(@RequestParam("memberPw") String memberPw,
                               Model model, HttpSession session) {
        return memberPwModifyService.execute(session, model, memberPw);
    }
    @PostMapping("/myPwUpdate")
    @ResponseBody // html이 아닌 값을 전달 할 때 사용 RestController와 같은 역할
    public boolean myPwUpdate(@RequestParam("oldPw") String oldPw,
                               @RequestParam(value="newPw") String newPw,
                               HttpSession session) {
        return myPassConfirmService.execute(newPw, oldPw, session);
    }
    @GetMapping("/memberDrop")
    public String memberDrop() {
        return "views/memberShip/memberDrop";
    }
    @PostMapping("/memberDropOk")
    public String memberDrop(
            @RequestParam("memberPw") String memberPw, Model model,
            HttpSession session) {
        int i = memberDropService.execute(memberPw, session, model);
        if(i == 1)
            return "redirect:/login/logout"; //탈퇴하면 로그아웃하기
        else return "views/memberShip/memberDrop"; // 현재 비밀번호가 틀리면 현재 페이지
    }
    @GetMapping("/memberUpdate")
    public String memberUpdate(HttpSession session, Model model) {
        memberInfoService.execute(session, model); //myModify에 내정보를 가지고 오기 위해서 myDetail에서 사용한 service를 쓴다.
        return "views/memberShip/MyModify";
    }

    @RequestMapping(value="/memberUpdate", method = RequestMethod.POST )
    public String memberUpdate(@Validated MemberCommand memberCommand, BindingResult result,
                               HttpSession session) {
        memberInfoUpdateService.execute(memberCommand, session, result);
        if(result.hasErrors()) {
            return "views/memberShip/MyModify";
        }
        return "redirect:/myPage/myDetail";
    }

}
