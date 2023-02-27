package mvcapp.config;

import mvcapp.model.Board;
import mvcapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class DB {
    private static List<Board> boardList = new ArrayList<>();
    private static List<User> userList = new ArrayList<>();
    static {
        boardList.add(new Board(1, "제목 ", "내용"));
        boardList.add(new Board(2, "제목 ", "내용"));
        userList.add(new User(1, "현주1", "1234", "yoon3798@naver.com"));
        userList.add(new User(2, "현주2", "4321", "yoon3798@gmail.com"));
    }

    public static List<Board> selectAll(){
        return boardList;
    }

    public static void insert(String title, String content){
        int id = boardList.size()+1;
        boardList.add(new Board(id, title, content));
    }

    public static User login(String username, String password) {
        User user = new User();
        for(User u : userList){
            if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
                user = u;
                break;
            }else {
                user = null;
            }
        }
        return user;
    }

    public static void join(String username, String password, String email) {
        int id = userList.size()+1;
        userList.add(new User(id, username, password, email));
    }
}