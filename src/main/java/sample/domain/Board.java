package sample.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import sample.domain.entry.BoardEntry;

/**
 * Board entity.
 * @author pmincz
 *
 */
@Document(collection = "board")
public class Board extends BaseEntity {

	@Indexed
	private Long boardId;
	private String title;
	
	public Board(BoardEntry entry) {
		super();
		this.title = entry.getTitle();
	}
	
	public Board() {}
	
	public Long getBoardId() {
		return boardId;
	}
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
