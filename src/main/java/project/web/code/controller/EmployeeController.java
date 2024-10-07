package project.web.code.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.ModelAndView;
import project.web.code.dto.EmployeeCommand;
import project.web.code.dto.EmployeeDTO;
import project.web.code.service.employees.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeAutoNumService employeeAutoNumService;
    private final EmployeeInsertService employeeInsertService;
    private final EmployeeListService employeeListService;
    private final EmployeeDeleteService employeeDeleteService;
    private final EmployeesDeleteService employeesDeleteService;
    private final EmployeeDetailService employeeDetailService;
    private final EmployeeUpdateService employeeUpdateService;
    private final EmployeeInfoService employeeInfoService;
    @GetMapping( "/employeeList")
        public ModelAndView empList(@RequestParam(name="nowPage", defaultValue = "0") int nowPage,
        @RequestParam(value = "searchWord", required = false) String searchWord) {
            //보여줄 화면과 전송할 데이터를 담는 객체
            ModelAndView view = new ModelAndView();
            try {

                //파라메터 객체생성
                Map<String, Object> param = new HashMap<>();
                param.put("nowPage", nowPage);
                param.put("searchWord", searchWord);
                //서비스로부터 결과 객체를 받는다
                EmployeeDTO.DateResponse response = employeeListService.employeeAllSelect(param);
                //전송할 데이터를 key , value 로 담는다.
                view.addObject("data", response);

            }catch (Exception e) {
                e.printStackTrace();
            }

            view.setViewName("views/employee/employeeList"); //이동할 화면경로

            return view;
        }

    @GetMapping("/empRegist")
    public String form(Model model){
        employeeAutoNumService.execute(model);
        return "views/employee/employeeForm";
    }


    @PostMapping( "/empRegist")
    public String form(@Validated EmployeeCommand employeeCommand, BindingResult result , Model model ) {
        // 정상적으로 저장되었다면 직원목록페이지로 이동
        if(result.hasErrors()) {
            // 오류가 있다면 employeeForm페이지가 열리게 합니다.
            return "views/employee/employeeForm";
        }

        // 모두 입력을 했다면 비밀번호확인 검사
        if (!employeeCommand.isEmpPwEqualsEmpPwCon()) {
            System.out.println("비밀번호 확인이 다릅니다.");
            //틀렸으면 다시 employeeForm페이지가 열리게 합니다.
            return "views/employee/employeeForm";
        }
        //모든 오류가 없으면 디비에 저장
        employeeInsertService.execute(employeeCommand);
        return "redirect:/employee/employeeList";
    }

    @PostMapping("/empsDelete")
    public String membersDelete(
            @RequestParam(value="empDels") String empsDel []) {
        employeesDeleteService.execute(empsDel);
        return "redirect:/employee/employeeList";
    }
    @GetMapping(value="/employeeDetail")
    public String employeeDetail(@RequestParam(value = "empNum") String empNum, Model model) {
        employeeDetailService.execute(empNum, model);
        return "views/employee/empDetail";
    }

    @GetMapping(value = "/empModify")
    public String employeeUpdate(@RequestParam(value = "empNum") String empNum, Model model) {
        employeeDetailService.execute(empNum, model);
        return "views/employee/employeeUpdate";
    }
    //데이터를 디비에 저장하기 위해 service를 만들어 줍니다.


    @PostMapping("/empModify")
    public String employeeUpdate(@Validated EmployeeCommand employeeCommand, BindingResult result) {
        // 유효성 검사하기 위해서 validated를 해줍니다.그리고 BindingResult 을 추합니다.
        // 오류 체크를 합니다.
        if (result.hasErrors()) {
            /// 오류가 있으면 현 페이지가 열리게 합니다.
            return "views/employee/employeeUpdate";
        }
        employeeUpdateService.execute(employeeCommand);
        //수정하고 직원상세페이지로
        return "redirect:/employee/employeeDetail?empNum=" + employeeCommand.getEmpNum();
    }


    @GetMapping("/empDelete")
    public String employeeDelete(@RequestParam(value = "empNum") String empNum) {
        // service를 실행 시켜줍니다.
        employeeDeleteService.execute(empNum);
        //직원 정보를 삭제한 후 직원목록 페이지로 이동합니다.
        return "redirect:/employee/employeeList";
        //인사담당자가 직원을 등록하는 것부터 직원 리스트 그리고 직원 수정 또한 직원 삭제 까지 봤습니다.
        //다음시간에는 직원 개인이 자신의 정보를 수정할 수 있고 비밀번호도 수정할 수 있도록 해 보겠습니다.
    }
    @GetMapping("/empMyPage")
    // 자신의 정보를 가져오기 위해선 로그인할 때 만들어진 session정보를 가져와야 합니다.
    public String empMyPage(HttpSession session, Model model) {
        employeeInfoService.execute(session, model);
        // html을 만들어 model에 있는 값을 출력합니다.
        return "views/worker/myInfo";
    }

}
