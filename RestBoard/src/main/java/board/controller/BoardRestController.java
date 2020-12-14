package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.entity.Board;
import board.service.BoardService;
import comment.entity.Comment;
import comment.service.CommentService;

@RestController
public class BoardRestController {
	@Autowired
	BoardService bsr;
	
	//완 게시물 리스트
	@GetMapping("/board")
	public Page<Board> GetBoardList (@RequestParam(value="page",defaultValue="1") int page) {
		System.out.println("게시판 리스트");
		Page<Board> BoardList=bsr.getBoardList(page);
		
		return BoardList;
	}
	//완 페이지 수 
	@SuppressWarnings("unchecked")
	@GetMapping("/board/page")
	public JSONObject GetPage() {
		JSONObject data=new JSONObject();
		String page=Integer.toString(bsr.count_page());
		data.put("page", page);
		return data;
	}
	
	//완
	@PostMapping("/board/post")
	public void insert_board(Board board,@RequestBody Map<String,Object> params) {
		System.out.println("게시글 작성");
		board.setContent((String)params.get("content"));
		board.setHeading((String)params.get("heading"));
		board.setName((String)params.get("name"));
		board.setTitle((String)params.get("title"));
		
		bsr.insert_board(board);
	}
	
	//완 댓글은 코멘트 컨트롤러에서
	@GetMapping("/board/post/{num}")
	public Board GetBoard(@PathVariable(value="num") int num) {
		System.out.println("게시판 상세보기");
		return bsr.getBoard(num);
	}
		
	
	//완
	@PutMapping("/board/post/{num}")
	public void update_board(Board board,@PathVariable("num") int num,@RequestBody Map<String,Object> params) {
		System.out.println("게시글 수정");
		board.setNum(num);
		board.setContent((String)params.get("content"));
		board.setHeading((String)params.get("heading"));
		board.setName((String)params.get("name"));
		board.setTitle((String)params.get("title"));
		bsr.update_board(board);
	}
		
	//완
	@DeleteMapping("/board/post/{num}")
	public void delete_board(Model model,@PathVariable(value="num") int num) {
		System.out.println("게시글 삭제");
		bsr.delete_board(num);
	}
	
	@GetMapping("/board/search")
	public String searchBoard(Model model,@RequestParam(value="Conditon",required=false) String Condition,@RequestParam(value="Keyword",required=false) String Keyword,@RequestParam(value="page",defaultValue="1") int page,HttpServletRequest req) {
		HttpSession session=req.getSession();
		
		System.out.println("게시글 찾기");		
		if(session.getAttribute("Condition") == null) {
			session.setAttribute("Condition", Condition);
		}else {
			if(Condition==null) {
				Condition=(String) session.getAttribute("Condition");
			}
		}
		
		if(session.getAttribute("Keyword")==null) {
			session.setAttribute("Keyword", Keyword);
		}else {
			if(Keyword==null) {
				Keyword=(String)session.getAttribute("Keyword");
			}
		}
		System.out.println(Condition+" "+Keyword);
		
		Page<Board> boardList=bsr.search_Board(Condition, Keyword, page);
		long size=boardList.getTotalElements()-(page*15);
		model.addAttribute("boardList", boardList);
		
		System.out.println(size);
		
		if(size<=0) {
			model.addAttribute("pageCnt",page);
		}else {
			model.addAttribute("pageCnt",page+1);
		}
		
		return "board/BoardSearchList";
	}
}
