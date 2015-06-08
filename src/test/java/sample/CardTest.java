package sample;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import sample.domain.Board;
import sample.domain.Card;
import sample.domain.entry.CardEntry;
import sample.repository.BoardRepository;
import sample.repository.CardRepository;
import sample.service.CounterService;
import sample.util.AbstractRestTest;
import sample.util.IntegrationTestUtil;

/**
 * board tests
 * @author pmincz
 *
 */
public class CardTest extends AbstractRestTest {
	
	@Autowired
	private CardRepository cardRepo;
	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private CounterService counter;

	@Test
	public void getCards() throws Exception {
		// @formatter:off
		mvc.perform(get("/cards")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		// @formatter:on
	}
	
	@Test
	public void getCardException() throws Exception {
		// @formatter:off
		mvc.perform(get("/cards/-1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(404));
		// @formatter:on
	}
	
	@Test
	@Rollback
	public void postCard() throws Exception {
		Board board = new Board();
		board.setBoardId(counter.getNextBoardIdSequence());
		board.setTitle("TO DO");
		board = boardRepo.save(board);
		
		CardEntry entry = new CardEntry("title", "description", board.getBoardId());
		
		mvc.perform(
						post("/cards").contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(IntegrationTestUtil.convertObjectToJsonBytes(entry)))
								.andExpect(status().isCreated())
								.andExpect(jsonPath("$.title", is("title")));
	}
	
	@Test
	@Rollback
	public void postCardWithoutBoard() throws Exception {
		CardEntry entry = new CardEntry("title", "description", -1L);
		
		mvc.perform(
						post("/cards").contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(IntegrationTestUtil.convertObjectToJsonBytes(entry)))
								.andExpect(status().is(404));
	}
	
	@Test
	@Rollback
	public void deleteCard() throws Exception {
		postCard();
		Card card = cardRepo.findByTitle("title");
		
		mvc.perform(MockMvcRequestBuilders.delete("/cards/"+card.getCardId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	@Rollback
	public void updateCard() throws Exception {
		postCard();
		Card card = cardRepo.findByTitle("title");
		
		CardEntry entry = new CardEntry("title 222", "description", card.getBoardId());
		
		mvc.perform(
				MockMvcRequestBuilders.put("/cards/"+card.getCardId())
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(IntegrationTestUtil.convertObjectToJsonBytes(entry)))
								.andExpect(status().isOk())
								.andExpect(jsonPath("$.title", is("title 222")));;
	}

}
