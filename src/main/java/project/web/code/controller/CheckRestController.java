package project.web.code.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.web.code.dto.FileCommand;
import project.web.code.service.EmailCheckService;
import project.web.code.service.FileDelService;
import project.web.code.service.UserEmailCheckService;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class CheckRestController {

    private final EmailCheckService emailCheckService;
    private final UserEmailCheckService userEmailCheckService;
    private final FileDelService fileDelService;

    @PostMapping(value="/checkRest/userEmailCheck")
    public String userEmailCheck(@RequestParam(value="userEmail") String userEmail) {
        String resultEmail = emailCheckService.execute(userEmail);
        if(resultEmail == null) {
            return "사용가능한 Email입니다.";
        }else {
            return "사용중인 Email입니다.";
        }
    }
    //@RestAPI를 이용해보자..
    @RequestMapping("/userConfirm")
    public String userConfirm(@RequestParam(value="chk") String chk) {
        int i = userEmailCheckService.execute(chk);;
        if(i == 0){
            return "이미 인증되었습니다. ";
        }else{
            return "인증되었습니다.";
        }
    }

    @PostMapping("/goods/fileDel")
    public String fileDel(FileCommand fileCommand, HttpSession session) {
        return fileDelService.execute(fileCommand, session);
    }


}
