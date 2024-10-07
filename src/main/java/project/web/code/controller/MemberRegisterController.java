package project.web.code.controller;

import lombok.RequiredArgsConstructor;
import project.web.code.dto.MemberCommand;
import project.web.code.service.memberRegister.UserWriteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class MemberRegisterController {

    private final UserWriteService userWriteService;
    /*모든 주소에서 memberCommand를 사용할 수 있게 정의 해준다.*/
    @ModelAttribute
    public MemberCommand memberCommand(){

        return new MemberCommand();
    }

    @GetMapping(value = "/userWrite")
    public String userWrite(){
        return "views/memberRegist/userForm";

    }

    @PostMapping(value = "/userRegist" )
    public String userRegist(@Validated MemberCommand memberCommand, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "views/memberRegist/userForm";
        }
        if (!memberCommand.isMemberPwEqualsMemberPwCon()) {
            //비밀번호와 비밀번호 확인이 다른 경우에도 메세지를 보내기
            //result.rejectValue(필드명, 에러코드, 메세지)
            result.rejectValue("memberPwCon", "MemberCommand.memberPwCon", "비밀번호 확인이 틀렸습니다. ");
            return "views/memberRegist/userForm";
        } else {
            userWriteService.execute(model, memberCommand);
            return "views/memberRegist/welcome";
        }
    }
}
