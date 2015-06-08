package sample.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import sample.domain.entry.CardEntry;

/**
 * Card entity.
 * @author pmincz
 *
 */
@Document(collection = "card")
public class Card extends BaseEntity {

	@Indexed
	private Long cardId;
	private String title;
	private String description;
	private Long boardId;
	
	public Card(CardEntry entry) {
		super();
		this.title = entry.getTitle();
		this.description = entry.getDescription();
		this.boardId = entry.getBoardId();
	}
	
	public Card() {}
	
	public Long getCardId() {
		return cardId;
	}
	public void setCardId(Long cardId) {
		this.cardId = cardId;
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
