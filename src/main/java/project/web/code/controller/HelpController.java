package project.web.code.controller;

import lombok.RequiredArgsConstructor;
import project.web.code.service.FindIdService;
import project.web.code.service.FindPwService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/help")
@RequiredArgsConstructor
public class HelpController {

   private final FindIdService findIdService;
   private final FindPwService findPwService;

    @GetMapping( "/findId")
    public String findId(){
        return "views/help/findId";
    }

    @PostMapping("/findId")
    public String findId(
            @RequestParam("userPhone")String userPhone,
            @RequestParam("userEmail")String userEmail,
            Model model) {
        findIdService.execute(userPhone, userEmail, model);
        return "views/help/findIdOk";
    }

    @GetMapping("/findPassword")
    public String findPassword() {
        return "views/help/findPw";
    }


    @PostMapping("/findPassword")
    public String findPassword(
            @RequestParam(value="userId")String userId,
            @RequestParam(value="userPhone") String userPhone,
            Model model) {
        findPwService.execute(userId, userPhone, model);
        return "views/help/findPwOk";
    }
}
