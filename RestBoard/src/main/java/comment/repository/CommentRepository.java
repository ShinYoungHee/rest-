package comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import comment.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
	public List<Comment> findBypostNum(int num);	
	@Transactional
	public void deleteAllBypostNum(int num);		//�Խñ� ���� �ɶ� ��۵� ���� ����
}
