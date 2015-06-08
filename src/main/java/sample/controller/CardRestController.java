package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sample.controller.util.BaseController;
import sample.domain.Card;
import sample.domain.entry.CardEntry;
import sample.service.CardService;

/**
 * User Rest Controller
 * @author pmincz
 *
 */
@RestController
@RequestMapping("/cards")
public class CardRestController extends BaseController<Card, CardEntry> {

	@Autowired
	private CardService service;

}