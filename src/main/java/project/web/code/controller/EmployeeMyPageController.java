package project.web.code.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.web.code.dto.EmployeeCommand;
import project.web.code.service.employees.EmployInfoService;
import project.web.code.service.employees.EmployeeInfoService;
import project.web.code.service.employees.EmployeeInfoUpdateService;
import project.web.code.service.employees.EmployeePassConfirmService;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeMyPageController {

    private final EmployInfoService employInfoService;
    private final EmployeeInfoService employeeInfoService;
    private final EmployeeInfoUpdateService employeeInfoUpdateService;
    private final EmployeePassConfirmService employeePassConfirmService;

    @GetMapping("/emMyPage")
    public String empMyPage(HttpSession session, Model model) {
        //자신의 정보를 가져오기 위해선 로그인 할 때 만들어진 session정보를 가져와야 한다.
        employInfoService.execute(session, model);
        return "views/worker/myInfo";
    }

    @GetMapping("/employeeUpdate")
    // 여기서도 자신의 정보를 가져오기 위해선 로그인할 때 만들어진 session정보를 가져와야 합니다.
    public String employeeUpdate(HttpSession session, Model model) {
        // myModify.html에 출력되는 값이 myInfo.html에서 사용하는 값이 같으므로 같은 service를 사용합니다.
        employeeInfoService.execute(session, model);
        return "views/worker/myModify";
    }
    @PostMapping("/employeeUpdate")
    public String employeeUpdate(@Validated EmployeeCommand employeeCommand
            , BindingResult result, HttpSession session) {
        employeeInfoUpdateService.execute(employeeCommand, session, result);
        if(result.hasErrors()) {
            ///수정시 오류가 있으면 현재 페이지로...
            return "views/worker/myModify";
        }else {
            // 수정이 완료되면 내정보 페이지로
            return "redirect:empMyPage";
        }
    }
    @GetMapping("/employeePwModify")
    public String employeePwModify() {
        return "views/worker/myPwCon";
    }
    @PostMapping("/employeePwModify")
    public String employeePwModify(@RequestParam("empPw")String empPw, Model model, HttpSession session) {
        return "views/worker/myNewPw";
    }
    @PostMapping("/empPwUpdate")
    public @ResponseBody boolean empPwUpdate(
                                              @RequestParam("oldPw")String oldPw, @RequestParam("newPw") String newPw,
                                              HttpSession session) {
        return employeePassConfirmService.execute(newPw, oldPw, session);
    }

}
