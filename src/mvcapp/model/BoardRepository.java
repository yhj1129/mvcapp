package mvcapp.model;


import mvcapp.config.DB;

import java.util.List;

//책임 : 데이터베이스 접근
public class BoardRepository {
    public List<Board> findAll(){
        // SELECT * FROM board 를 리턴하는 거임
        return DB.selectAll();
    }


    //클라이언트가 DS에 내용 전달, DS는 내용을 컨트롤러에 전달, 컨트롤러는 내용 레포지토리에 전달
    public void save(String  title, String content){
        //INSERT INTO board(title, content) VALUES('제목1', '내용1')
        DB.insert(title, content);
    }
}
