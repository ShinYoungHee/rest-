package comment.entity;

import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comment
 *
 */
@Entity
@Table(name="comment")
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Num;
	private String writer;
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate;
	private int postNum;
	
	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}

	

	public Date getRegDate() {
		return regDate;
	}


	public void setReg_Date(Date regDate) {
		this.regDate = regDate;
	}


	public int getPostNum() {
		return postNum;
	}


	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}


	public int getNum() {
		return Num;
	}


	public void setNum(int Num) {
		this.Num = Num;
	}

	
	public Comment() {
		super();
	}
   
}
