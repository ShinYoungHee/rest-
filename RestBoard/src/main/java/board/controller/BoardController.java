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

@Controller
public class BoardController {
	@Autowired
	BoardService bsr;
	@Autowired
	CommentService csr;
	
	
	@GetMapping({"/","/board"})
	public String GetBoardList (Model model,@RequestParam(value="page",defaultValue="1") int page) {
		System.out.println("게시판");
		
		Page<Board> BoardList=bsr.getBoardList(page);
		model.addAttribute("boardList",BoardList);
		model.addAttribute("pageCnt",bsr.count_page());
		
		return "board/BoardList";

	}
	
	@GetMapping("/board/{num}")
	public String GetBoard(Model model,@PathVariable(value="num") int num) {
		System.out.println("게시판 상세보기");
		model.addAttribute("board",bsr.getBoard(num));
		List<Comment> comment=csr.get_comment(num);
		model.addAttribute("comment",csr.get_comment(num));
		return "board/getBoard";
	}
	
	@GetMapping("/insertBoard")
	public String InsertBoard(Model model) {
		System.out.println("글 쓰기 이동");
		return "board/insertBoard";
	}
	
	@PostMapping("/board/post")
	public String insert_board(Model model,Board board,HttpServletRequest req) {
		System.out.println("게시글 작성");
		bsr.insert_board(board,req);
		return "redirect:/board";
	}
	

	@GetMapping("/board/post/{num}")
	public String UpdateBoard(Model model,@PathVariable(value="num") int num) {
		System.out.println("게시글 수정 페이지 이동");
		model.addAttribute("board",bsr.getBoard(num));
		return "board/updateBoard";
	}
	

	@PutMapping("/board/post/{num}")
	public String update_board(Model model,Board board,@PathVariable("num") int num) {
		System.out.println("게시글 수정");
		bsr.update_board(board);
		
		return "redirect:/board/";
	}
		
	@DeleteMapping("/board/post/{num}")
	public String delete_board(Model model,@PathVariable(value="num") int num) {
		System.out.println("게시글 삭제");
		bsr.delete_board(num);
		
		return "redirect:/board/";
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
