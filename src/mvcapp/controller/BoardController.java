package mvcapp.controller;

import mvcapp.config.ViewResolver;
import mvcapp.model.Board;
import mvcapp.model.BoardRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

// 책임 : 요청 받고 응답하기 (view or Data로)
public class BoardController {

    //Controller -> Repository 필요로 함
    // Controller는 Repository에 의존적이다
    //
    private BoardRepository boardRepository;

    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    public String list(HttpServletRequest request){

        List<Board> boardList = boardRepository.findAll();
        request.setAttribute("boardList", boardList);
        return ViewResolver.resolve("/board/list");
    }
    public String saveForm(){
        return ViewResolver.resolve("/board/saveForm");
    }
    public String save(String title, String content) { // 스프링은 컨트롤러에 매개변수를 적기만 하면 formUrlEncoded 데이터를 DS로부터 전달받음
        //검증 코드 : http 메서드 4가지 중에 검증해야하는것은
        //post와 put, 이 둘은 클라이언트로부터 resourse를 전달받기 때문에
        // 나머지는 바디가 없어서 괜찮음

        if (title ==null || title.equals("")){
            throw new NullPointerException("title이 없습니다");
        }

        if (content ==null || content.equals("")){
            throw new NullPointerException("content가 없습니다");
        }
        boardRepository.save(title, content);
        return "/board/list.do";
    }
}
