package sample.domain.entry;

/**
 * Card entry for the rest services
 * @author pmincz
 *
 */
public class CardEntry {
	
	private String title;
	private String description;
	private Long boardId;
	
	public CardEntry() {}
	
	public CardEntry(String title, String description, Long boardId) {
		this.title = title;
		this.description = description;
		this.boardId = boardId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

}
