package sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.domain.Board;
import sample.domain.entry.BoardEntry;
import sample.exception.MyResourceNotFoundException;
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
public class BoardService implements ServiceInterface<Board, BoardEntry> {

	@Autowired
	private CardRepository cardRepo;
	@Autowired
	private CounterService counter;
	@Autowired
	private BoardRepository boardRepo;

	public List<Board> getAll() {
		return boardRepo.findAll();
	}
	
	public Board getById(Long id) {
		return RestPreconditions.checkFound(boardRepo.findByBoardId(id));
	}
	
	public Board create(BoardEntry entry) {
		Board board = new Board(entry);
		board.setBoardId(counter.getNextBoardIdSequence());
		return boardRepo.save(board);
	}
	
	public void delete(Long id) {
		Board board = RestPreconditions.checkFound(boardRepo.findByBoardId(id));
		try {
			RestPreconditions.checkFound(cardRepo.findByBoardId(board.getBoardId()).size() == 0);
		} catch (MyResourceNotFoundException e) {
			throw new MyResourceNotFoundException("The board must be empty");
		}
		boardRepo.delete(board.getId());
	}
	
	public Board update(Long id, BoardEntry entry) {
		Board update = RestPreconditions.checkFound(boardRepo.findByBoardId(id));
		update.setTitle(entry.getTitle());
		return boardRepo.save(update);
	}

}
