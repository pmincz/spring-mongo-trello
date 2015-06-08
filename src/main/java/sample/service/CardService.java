package sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.domain.Card;
import sample.domain.entry.CardEntry;
import sample.exception.RestPreconditions;
import sample.repository.BoardRepository;
import sample.repository.CardRepository;
import sample.service.util.ServiceInterface;

/**
 * Counter service
 * @author pmincz
 *
 */
@Service
public class CardService implements ServiceInterface<Card, CardEntry> {

	@Autowired
	private CardRepository cardRepo;
	@Autowired
	private CounterService counter;
	@Autowired
	private BoardRepository boardRepo;

	public List<Card> getAll() {
		return cardRepo.findAll();
	}
	
	public Card getById(Long id) {
		return RestPreconditions.checkFound(cardRepo.findByCardId(id));
	}
	
	public Card create(CardEntry entry) {
		Card card = new Card(entry);
		RestPreconditions.checkFound(boardRepo.findByBoardId(card.getBoardId()));
		card.setCardId(counter.getNextCardIdSequence());
		return cardRepo.save(card);
	}
	
	public void delete(Long id) {
		Card card = RestPreconditions.checkFound(cardRepo.findByCardId(id));
		cardRepo.delete(card.getId());
	}
	
	public Card update(Long id, CardEntry entry) {
		Card update = RestPreconditions.checkFound(cardRepo.findByCardId(id));
		update.setTitle(entry.getTitle());
		update.setDescription(entry.getDescription());
		update.setBoardId(entry.getBoardId());
		RestPreconditions.checkFound(boardRepo.findByBoardId(update.getBoardId()));
		return cardRepo.save(update);
	}

	public List<Card> getAllCardsByBoardId(Long id) {
		return cardRepo.findByBoardId(id);
	}

}
