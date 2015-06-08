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
import sample.domain.entry.BoardEntry;
import sample.repository.BoardRepository;
import sample.util.AbstractRestTest;
import sample.util.IntegrationTestUtil;

/**
 * board tests
 * @author pmincz
 *
 */
public class BoardTest extends AbstractRestTest {
	
	@Autowired
	private BoardRepository boardRepo;

	@Test
	public void getBoards() throws Exception {
		// @formatter:off
		mvc.perform(get("/boards")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		// @formatter:on
	}
	
	@Test
	public void getBoardException() throws Exception {
		// @formatter:off
		mvc.perform(get("/boards/-1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(404));
		// @formatter:on
	}
	
	@Test
	@Rollback
	public void postBoard() throws Exception {
		BoardEntry boardEntry = new BoardEntry("TO DO");
		
		mvc.perform(
						post("/boards").contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(IntegrationTestUtil.convertObjectToJsonBytes(boardEntry)))
								.andExpect(status().isCreated())
								.andExpect(jsonPath("$.title", is("TO DO")));
	}
	
	@Test
	@Rollback
	public void deleteBoard() throws Exception {
		postBoard();
		Board board = boardRepo.findByTitle("TO DO");
		
		mvc.perform(MockMvcRequestBuilders.delete("/boards/"+board.getBoardId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	@Rollback
	public void updateBoard() throws Exception {
		postBoard();
		Board board = boardRepo.findByTitle("TO DO");
		
		BoardEntry entry = new BoardEntry("TO DO 2");
		
		mvc.perform(
				MockMvcRequestBuilders.put("/boards/"+board.getBoardId())
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.content(IntegrationTestUtil.convertObjectToJsonBytes(entry)))
								.andExpect(status().isOk())
								.andExpect(jsonPath("$.title", is("TO DO 2")));;
	}

}
