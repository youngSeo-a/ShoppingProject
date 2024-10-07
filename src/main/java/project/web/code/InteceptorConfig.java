package project.web.code;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import project.web.code.dto.AuthInfoDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@Component
//public class InteceptorConfig  implements HandlerInterceptor{
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception { //controller에 들어오기전에 캐치하는 함수입니다.
//        //  로그인이 안되어 있으면 main으로 가도록 설정해줍니다.
//        HttpSession session = request.getSession();
//        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
//        if(ObjectUtils.isEmpty(auth)){ // 로그인 정보가 없다면
//            response.sendRedirect("/login/userLogin");
//            return false;
//        }else{
//            return true;
//        }
//    }
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//        // TODO Auto-generated method stub
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//        // TODO Auto-generated method stub
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
//}

