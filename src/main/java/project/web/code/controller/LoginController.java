package project.web.code.controller;

import lombok.RequiredArgsConstructor;
import project.web.code.dto.LoginCommand;
import project.web.code.service.FindIdService;
import project.web.code.service.IdcheckService;
import project.web.code.service.UserLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final IdcheckService idCheckService;
    private final UserLoginService userLoginService;

    @PostMapping("/userIdCheck")
//    html문서가 아닌 텍스트를 전달하기 위해서는 @ResponseBody이 필요
    public @ResponseBody String userIdCheck(@RequestParam(value="userId") String userId){
        String resultId=  idCheckService.execute(userId);

        if (resultId == null){
            return "사용가능한 아아디입니다.";
        }else {
            return "사용중인 아이디입니다.";
        }
    }
    @GetMapping("/login")
    public String login(LoginCommand loginCommand) { // 여기에 command추가
        return "views/loginForm2";
    }

    @PostMapping("/login")
    public String login(@Validated LoginCommand loginCommand, BindingResult result, HttpSession session
            , HttpServletResponse response) {
        userLoginService.execute(loginCommand, session, result, response);
        //오류가 있으면 index.html페이지 열리게 만들자
        if(result.hasErrors()) {
            return "redirect:/login/login";
        }
        return "redirect:/emp";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        //로그아웃에서 해당 쿠키만 삭제합니다.
        Cookie cookie = new Cookie("autoLogin", "");
        cookie.setPath("/");
        cookie.setMaxAge(0); // 해당쿠키 이름에 수명을 0으로 줘서 다시 사용자에게 보냅니다.
        response.addCookie(cookie);
        session.invalidate(); // 로그아웃시 모든 session삭제
        return "redirect:/emp"; // 그리고 첫 페이지로
        // 자동로그인은 로그아웃 하기 전 까지는 계속 로그인이 됩니다./
    }

    @GetMapping("/userLogin")
    public String userlogin(LoginCommand loginCommand) { // 여기에 command추가
        return "views/userLoginForm";
    }

    @PostMapping("/userLogin")
    public String userlogin(@Validated LoginCommand loginCommand, BindingResult result, HttpSession session
            , HttpServletResponse response) {
        userLoginService.execute(loginCommand, session, result, response);
        //오류가 있으면 index.html페이지 열리게 만들자
        if(result.hasErrors()) {
            return "redirect:/login/userLogin";
        }
        return "redirect:/shopping";
    }

    @GetMapping("/UserLogout")
    public String userlogout(HttpSession session, HttpServletResponse response) {
        //로그아웃에서 해당 쿠키만 삭제합니다.
        Cookie cookie = new Cookie("autoLogin", "");
        cookie.setPath("/");
        cookie.setMaxAge(0); // 해당쿠키 이름에 수명을 0으로 줘서 다시 사용자에게 보냅니다.
        response.addCookie(cookie);
        session.invalidate(); // 로그아웃시 모든 session삭제
        return "redirect:/shopping"; // 그리고 첫 페이지로
        // 자동로그인은 로그아웃 하기 전 까지는 계속 로그인이 됩니다./
    }


    @GetMapping("/item.login")
    public String item(LoginCommand loginCommand) { // 여기에 command추가
        return "views/loginForm";
    }
    @PostMapping("/item.login")
    public String item(@Validated LoginCommand loginCommand,BindingResult result  , //유효성검사를 합니다.
                       HttpSession session, HttpServletResponse response) {
        //이전에 사용했던 로그인service를 사용합니다.
        userLoginService.execute(loginCommand, session, result, response);
        if(result.hasErrors()) {
            // 입력하지 않은 값이 있으면 다시 페이지를 로딩
            return "views/loginForm";
        }
        // 정상적으로 로그인 됭었을 때 코드를 작성합니다.
        // 정상적으로 로그인 되었다면 popup창을 닫고 부모창은 새로고침을 하게 합니다.
        // 그러기 위해선 servlet코드를 작성하겠습니다.
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 자바스크립트코드를 작성합니다.
        String str = "<script language='javascript'>"
                + " opener.location.reload();"
                + " window.self.close();"
                + " </script>";
        out.print(str);
        out.close();
        return null;
//        return "redirect:/shopping";
    }
    @GetMapping("/item.logout")
    public String item(HttpSession session, HttpServletResponse response) {
        //로그아웃에서 해당 쿠키만 삭제합니다.
        Cookie cookie = new Cookie("autoLogin", "");
        cookie.setPath("/");
        cookie.setMaxAge(0); // 해당쿠키 이름에 수명을 0으로 줘서 다시 사용자에게 보냅니다.
        response.addCookie(cookie);
        session.invalidate(); // 로그아웃시 모든 session삭제
        return "redirect:/shopping"; // 그리고 첫 페이지로
        // 자동로그인은 로그아웃 하기 전 까지는 계속 로그인이 됨./
    }
}
