package sample.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import sample.domain.Card;

/**
 * Mongo repository for the cards
 * @author pmincz
 *
 */
public interface CardRepository extends MongoRepository<Card, String> {
	public Card findByCardId(Long cardId);
	public List<Card> findByBoardId(Long boardId);
	public Card findByTitle(String title);
}
