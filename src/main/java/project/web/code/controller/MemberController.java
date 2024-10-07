package project.web.code.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.web.code.dto.MemberCommand;
import project.web.code.dto.MemberDTO;
import project.web.code.service.member.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberAutoNumService memberAutoNumService;
    private final MemberListService memberListService;
    private final MemberInsertService memberInsertService;
    private final MembersDeleteService membersDeleteService;
    private final MemberDetailService memberDetailService;
    private final MemberUpdateService memberUpdateService;
    private final MemberDeleteService memberDeleteService;

    @GetMapping("/memberList")
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
            MemberDTO.DateResponse response = memberListService.selectAll(param);
            //전송할 데이터를 key , value 로 담는다.
            view.addObject("data", response);

        }catch (Exception e) {
            e.printStackTrace();
        }

        view.setViewName("views/member/memberList"); //이동할 화면경로

        return view;
    }


    @GetMapping("/memberRegist")
    public String form(Model model){
        // 회원번호를 불러오도록 한다.
        memberAutoNumService.execute(model);
        return "views/member/memberForm";
    }

    @PostMapping("/memberRegist")
    public String form(@Validated MemberCommand memberCommand, BindingResult result) {
        //오류가 있으면 오류 메세지를 html에 전달
        if (result.hasErrors()){
            return "views/member/memberForm";
        }
        if(!memberCommand.isMemberPwEqualsMemberPwCon()){
            //비밀번호와 비밀번호 확인이 다른 경우에도 메세지를 보내기
            //result.rejectValue(필드명, 에러코드, 메세지)
            result.rejectValue("memberPwCon", "MemberCommand.memberPwCon", "비밀번호 확인이 틀렸습니다. ");
            return "views/member/memberForm";
        }else{
            memberInsertService.execute(memberCommand);
            return "redirect:memberList";
        }
    }
    @GetMapping("/memberDelete/{memberNum}")
    public String memberdelete(
            @PathVariable(value="memberNum") String memberNum) {
        memberDeleteService.execute(memberNum);
        return "redirect:../memberList";
    }

    @PostMapping("/memberDelete")
    public String dels(@RequestParam(value="memDels") String memDeles[]){
        membersDeleteService.execute(memDeles);
        return "redirect:memberList";
    }

    @GetMapping("/memberDetail")
    public String memberDetail(@RequestParam(value = "memberNum") String memberNum , Model model){
        memberDetailService.execute(memberNum, model);
        return "views/member/memberInfo";
    }

    @GetMapping("/memberUpdate")
    public String memberUpdate(
            @RequestParam(value="memberNum") String memberNum, Model model) {
        memberDetailService.execute(memberNum, model);
        return "views/member/memberModify";
    }
    @PostMapping("/memberModify")
    public String memberModify(@Validated  MemberCommand memberCommand, BindingResult result) {
        //html에서 넘오온 값은 MemberCommand가 받는다. 이때 MemberCommand에 넘어오지 않은 경우 오류 검사를 하게 한다.
        if(result.hasErrors()) {
            //오류가 있다면 다시 memberModify페이지가 열리게 한다.
            // memberModify페이지에 MemberCommand가 가진 값을 전달 하게 한다.
            // MemberCommand는 값만 전달하는 것이 아니라 오류 메시지도 전달한다.
            return "views/member/memberModify";
        }
        memberUpdateService.execute(memberCommand);
        return "redirect:memberDetail?memberNum="+memberCommand.getMemberNum();
    }
}
