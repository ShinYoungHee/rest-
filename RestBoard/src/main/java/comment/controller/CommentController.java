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

@Controller
public class CommentController {
	@Autowired
	CommentService cs;
	
	@PostMapping("/comment/{num}")
	public String insert_comment(Model model,@PathVariable(value="num") int num,Comment comment,HttpServletRequest req) {
		cs.insert_commentservice(num, comment,req);
		
		return "redirect:/board/"+num;
	}
	
}
