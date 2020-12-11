package board.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import board.entity.Board;
import board.repository.BoardRepository;
import comment.repository.CommentRepository;

@Service
public class BoardService {
	private static final int BLOCK_PAGE=15;		//���������� ������ �ִ� �Խù� ��
	
	@Autowired
	private BoardRepository br;
	@Autowired
	private CommentRepository cr;
	
	//�Խñ۸���Ʈ �ҷ�����
	public Page<Board> getBoardList(int page) {
		Pageable pageable=PageRequest.of(page-1,BLOCK_PAGE,Sort.by("num").descending());
		return br.findAll(pageable);
	}
	
	//�Խñ� ã�� ����
	public Page<Board> search_Board(String Condition,String Keyword,int page){
		Pageable pageable=PageRequest.of(page-1, BLOCK_PAGE);
		if(Condition.equals("all")) {
			return br.findBytitleContainingOrContentContainingOrderByNumDesc(Keyword, Keyword,pageable);
		}else if(Condition.equals("title")) {
			return br.findBytitleContainingOrderByNumDesc(Keyword,pageable);
		}else if(Condition.equals("content")) {
			return br.findBycontentContainingOrderByNumDesc(Keyword,pageable);
		}else {
			return null;
		}
	}
	
	//�Խñ� ��������
	public long count_page() {
		double count=(double)br.count();
		int page=(int)Math.ceil(count/BLOCK_PAGE);
		
		return page;
	}
	
	//�Խñ� Ȯ��
	public Board getBoard(int num) {
		Board board=br.findBynum(num);
		board.setRead_cnt(board.getRead_cnt()+1);
		br.save(board);
		return br.findBynum(num);
	}
	
	//�Խñ� �ۼ� ����
	public void insert_board(Board board,HttpServletRequest req) {
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy:MM:dd HH:mm:ss ");
		String time=format.format(date);
		try {
			board.setRegDate(format.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		br.save(board);
	}
	
	//�Խñ� ����
	public void update_board(Board board) {
		Board brd=br.findBynum(board.getNum());
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		String time=format.format(date);
		
		try {
			brd.setRegDate(format.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		brd.setTitle(board.getTitle());
		brd.setContent(board.getContent());
		brd.setHeading(board.getHeading());
		br.save(brd);
	}
	
	public void delete_board(int num) {
		
		br.deleteAllBynum(num);
		cr.deleteAllBypostNum(num);
	}
}
