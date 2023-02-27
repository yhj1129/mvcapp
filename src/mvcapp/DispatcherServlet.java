package mvcapp;

import mvcapp.controller.BoardController;
import mvcapp.controller.UserController;
import mvcapp.model.BoardRepository;
import mvcapp.model.User;
import mvcapp.model.UserRepository;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * GET -> http://localhost:8080/board/list.do
 * GET -> http://localhost:8080/board/saveForm.do
 * POST -> http://localhost:8080/board/save.do
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 버퍼로 들어오는 모든 값을 UTF-8로 인코딩해서 받기
        req.setCharacterEncoding("utf-8");

        //2. path를 파싱
        String path = getPath(req);
        System.out.println("path: " + path);

        //3. action 파싱
        String action = getAction(req);
        System.out.println("action: " + action);

        String method = req.getMethod();
        System.out.println(method);
        //4. 컨트롤러 객체 생성
        BoardController boardCon = new BoardController(new BoardRepository()); //DI 의존성 주입
        UserController userCon = new UserController(new UserRepository());
        HttpSession session = req.getSession();

        //5. 라우팅하기 User
        if(path.equals("user")){
            switch (action){
                case "joinForm":
                    String joinFormView = userCon.joinForm();
                    req.getRequestDispatcher(joinFormView).forward(req, resp);
                    break;
                case "loginForm":
                    String loginFormView = userCon.loginForm();
                    req.getRequestDispatcher(loginFormView).forward(req, resp);
                    break;
                case "join":
                    if(!method.equals("POST")){
                        resp.sendRedirect("/user/error.do");
                        break;
                    }
                    String username = req.getParameter("username");
                    String password = req.getParameter("password");
                    String email = req.getParameter("email");
                    String joinRedirect = userCon.join(username, password, email);

                    resp.sendRedirect(joinRedirect);
                    break;
                case "login":
                    System.out.println(method);
                    if(!method.equals("POST")){
                        resp.sendRedirect("/user/error.do");
                        break;
                    }
                    username = req.getParameter("username");
                    password = req.getParameter("password");
                    String loginRedirect = userCon.login(username, password);
                    User user = userCon.getUser(username, password);
                    if(user != null){
                        session.setAttribute("user", user);
                    }
                    resp.sendRedirect(loginRedirect);
                    break;
                case "error":
                    String errRedirect = userCon.getError();
                    req.getRequestDispatcher(errRedirect).forward(req, resp);
                    break;
                default:
                    System.out.println("default");
                    resp.sendRedirect("/user/loginForm.do");

            }
        }

        //5. 라우팅하기 Board
        else if(path.equals("board")){
            switch (action){
                case "saveForm":
                    String saveFormView = boardCon.saveForm();
                    req.getRequestDispatcher(saveFormView).forward(req, resp);//두번 안만들라고
                    break;
                case "save":
                    if(!method.equals("POST")){
                        resp.setContentType("text/html; charset=utf-8");
                        resp.getWriter().println("POST로 요청해야 합니다");
                        break;
                    }
                    String title = req.getParameter("title");
                    String content = req.getParameter("content");
                    String saveRedirect = boardCon.save(title, content);
                    resp.sendRedirect(saveRedirect);
                    break;
                case "list":
                    if(session.getAttribute("user") != null){ //user 값이 있으면 board보여줌
                        String listView = boardCon.list(req);
                        req.getRequestDispatcher(listView).forward(req, resp);
                        break;
                    }else {//없으면 로그인으로 돌아감
                        resp.sendRedirect("/user/loginForm.do");
                        break;
                    }
            }
        }
    }

    // board/list.do를 getUri로 받음
    private String getPath(HttpServletRequest req) {
        String path = getUri(req).split("/")[0]; // /를 기준으로 나누고 첫번째 것 board가져옴
        //System.out.println(path);
        return path;
    }
    // board/list.do
    private String getAction(HttpServletRequest req) {
        String action = getUri(req).split("/")[1]; // /를 기준으로 나누고 두번째 것 list가져옴
        action = action.replace(".do", ""); //do 지우고
        //System.out.println(action);
        return action;
    }

    //http://localhost:8080/board/list.do을 요청하면
    private String getUri(HttpServletRequest req) {
        String uri = req.getRequestURI(); // /board/list.do 가져옴
        uri = uri.substring(1); // 슬래시 하나 빼고 board/list.do 잘라줌
        return uri;
    }
}
