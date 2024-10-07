package project.web.code.service;

import lombok.RequiredArgsConstructor;
import project.web.code.dto.AuthInfoDTO;
import project.web.code.dto.LoginCommand;
import project.web.code.mapper.member.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;//회원가입시 비밀번호를 암호화 했으므로 암호를 비교하기 위헤 필요

    public void execute(LoginCommand loginCommand, HttpSession session, BindingResult result
                         , HttpServletResponse response){
        String userId = loginCommand.getUserId();
        String userPw = loginCommand.getUserPw();
        //회원 로그인 정보를 가지고 오기 위한 DTO필요
        AuthInfoDTO dto = userMapper.loginSelect(userId);
        if(userId != "" && userId != null) {
            if(dto != null) {// 회원아이디가 있으면 dto가 null이 아님.
                if(dto.getUserEmailCheck() == null) {
                    System.out.println("아이디는 있지만 이메일 인증이 되지 않았을 때");
                    result.rejectValue("userId", "loginCommand.userId"
                            ,"이메일 인증이 안되었습니다.");
                }else {
                    //아이디가 존재하고 비밀번호가 일치하는 경우
                    //                        본문(로그인창에서 입력 한 값)
                    //								   암호문(디비에서 가져온 암호화된 문자열)
                    if(passwordEncoder.matches(userPw, dto.getUserPw())) {
                        System.out.println("비밀번호가 일치");
                        //아이디와 비밀번호가 일치 하면 session에 로그인 정보 저장
                        session.setAttribute("auth", dto);
                        // 로그인이 정상적으로 되면 쿠키가 만들어지도록 합니다.
                        // 먼저 아이디 저장을 하기위 해 idStore를 읽어 오겠습니다.
                        //아이디 저장에 체크가 된 경우
                        if(loginCommand.getIdStore() != null && loginCommand.getIdStore()) {
                            System.out.println("쿠키 만들어짐");
                            // 쿠키를 만들어서 쿠키에 아이디를 저장하겠습니다.
                            // 쿠키 생성
                            Cookie cookie = new Cookie("idStore", loginCommand.getUserId());
                            // 저장 경로
                            cookie.setPath("/");
                            // 수명 주기
                            cookie.setMaxAge(60*60*24*30);
                            // 사용자에게 전송
                            response.addCookie(cookie); // response를 통해 사용자에게 전달합니다.
                        }else { // 아이디 저장에 체크를 해지한 경우
                            System.out.println("쿠키가 안 만들어짐..");
                            Cookie cookie = new Cookie("idStore", loginCommand.getUserId());
                            cookie.setPath("/");
                            cookie.setMaxAge(0); // 수명주기를 0으로 주면 쿠키는 삭제됩니다.
                            response.addCookie(cookie);
                        }
                        // 이제 자동로그인 하겠습니다. autoLogin이 있는지 확인
                        if(loginCommand.getAutoLogin() != null && loginCommand.getAutoLogin()) {
                            // 쿠키 생성 // 위와 같습니다. 쿠키 이름만 바궈주세요..
                            Cookie cookie = new Cookie("autoLogin", loginCommand.getUserId());
                            // 저장 경로
                            cookie.setPath("/");
                            // 수명 주기
                            cookie.setMaxAge(60*60*24*30);
                            // 사용자에게 전송
                            response.addCookie(cookie);
                        }
                    }else {
                        System.out.println("비밀번호가 일치하지 않을 때");
                        result.rejectValue("userPw", "loginCommand.userPw"
                                ,"비밀번호가 틀렸습니다.");
                    }
                }
            }else {
                System.out.println("아이디가 존재 하지 않았을 때");
                result.rejectValue("userId", "loginCommand.userId","아이디가 존재하지 않습니다.");
            }
        }

    }
}
