package mvcapp.controller;

import mvcapp.config.ViewResolver;
import mvcapp.model.Board;
import mvcapp.model.BoardRepository;
import mvcapp.model.User;
import mvcapp.model.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

// 책임 : 요청 받고 응답하기 (view or Data로)
public class UserController {


    private UserRepository userRepository;

    public String getError(){
        return ViewResolver.resolve("/err/error");
    }

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String joinForm() {
        return ViewResolver.resolve("/user/joinForm");
    }

    public String join(String username, String password, String email) {
        if (username == null || username.equals("") || password ==null || password.equals("") ||email ==null || email.equals("")){
            return "/user/error.do";
        } else{
            userRepository.join(username, password, email);
            return "/user/loginForm.do";
        }
    }

    public String loginForm() {
        return ViewResolver.resolve("/user/loginForm");
    }

    public String login(String username, String password) {

        if (username ==null || username.equals("") || password ==null || password.equals("")){
            return "/user/error.do";
        }else{
            User user = userRepository.login(username, password);
            if(user == null){
                return "/user/error.do";
            }else{
                return "/board/list.do";
            }
        }

    }

    public User getUser(String username, String password){
        User user = new User();
        if (username ==null || username.equals("") || password ==null || password.equals("")) {
            user = userRepository.login(username, password);
        }
        return user;
    }
}
