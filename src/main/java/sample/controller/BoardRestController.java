package sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sample.controller.util.BaseController;
import sample.domain.Board;
import sample.domain.Card;
import sample.domain.entry.BoardEntry;
import sample.service.BoardService;
import sample.service.CardService;

/**
 * User Rest Controller
 * @author pmincz
 *
 */
@RestController
@RequestMapping("/boards")
public class BoardRestController extends BaseController<Board, BoardEntry> {

	@Autowired
	private BoardService service;
	@Autowired
	private CardService cardService;
	
	/**
	 * Get all the cards by boardId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="{id}/cards")
	public List<Card> getAllCardsByBoardId(@PathVariable Long id) {
		return cardService.getAllCardsByBoardId(id);
	}

}