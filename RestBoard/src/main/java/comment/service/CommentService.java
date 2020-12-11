package comment.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.entity.Board;
import board.repository.BoardRepository;
import comment.entity.Comment;
import comment.repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	CommentRepository cr;
	@Autowired
	BoardRepository br;
	
	public void insert_commentservice(int num,Comment comment,HttpServletRequest req) {
		Board board=br.findBynum(num);
		board.setReview_cnt(board.getReview_cnt()+1);
		br.save(board);		//��� ���� ����
		
		String writer="dd";
		
		comment.setWriter(writer);
		
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		String time=format.format(date);
		try {
			comment.setReg_Date(format.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		comment.setPostNum(num);
		cr.save(comment);
	}
	
	public List<Comment> get_comment(int num) {
		System.out.println(num);
		List<Comment> list=cr.findBypostNum(num);
		return cr.findBypostNum(num);
	}
}
