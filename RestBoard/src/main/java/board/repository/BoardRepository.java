package board.repository;



import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board,Integer> {
	public Page<Board> findAll(Pageable page);	//�Խù� ��� �ҷ�����
	
	public Board findBynum(int num);		//�Խù� ��ȸ
	public Page<Board> findBycontentContainingOrderByNumDesc(String content,Pageable pageable);	//���� �˻�
	public Page<Board> findBytitleContainingOrderByNumDesc(String title,Pageable pageable);	//���� �˻�
	public Page<Board> findBytitleContainingOrContentContainingOrderByNumDesc(String title,String content,Pageable pageable);	//��ü �˻�
	
	@Transactional 
	public void deleteAllBynum(int num);		//�� ����
}
	