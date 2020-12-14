package comment.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import comment.entity.Comment;
import comment.service.CommentService;

@RestController
public class CommentController {
	@Autowired
	CommentService cs;
	
	//댓글리스트
	@GetMapping("/comment/{num}")
	public List<Comment> getComment(@PathVariable(value="num") int num) {
		System.out.println("댓글 등록");
		return cs.get_comment(num);
	}
	
	//댓글 쓰기
	@PostMapping("/comment/{num}")
	public void insertComment(@PathVariable(value="num") int num,@RequestBody Map<String,Object> params) {
		System.out.println("댓글 등록");
		Comment comment = new Comment();
		comment.setWriter((String)params.get("writer"));
		comment.setContent((String)params.get("content"));
		cs.insert_commentservice(num, comment);
	}
	
}

